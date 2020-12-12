package com.example.decorate.services;

import com.example.decorate.domain.DecorateUser;
import com.example.decorate.domain.NotificationEmail;
import com.example.decorate.domain.RefreshTokenRequest;
import com.example.decorate.domain.VerificationToken;
import com.example.decorate.domain.dto.AuthenticationResponse;
import com.example.decorate.domain.dto.LoginRequest;
import com.example.decorate.domain.dto.RegisterRequest;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.repositorys.security.DecorateUserRepository;
import com.example.decorate.repositorys.security.VerificationTokenRepository;
import com.example.decorate.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final DecorateUserRepository decorateUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Async
    public void signUp(RegisterRequest registerRequest) {
        String desiredUsername = registerRequest.getUsername();
        DecorateUser user = DecorateUser.builder()
                .username(desiredUsername)
                .simpleUsername(desiredUsername)
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .created(Instant.now())
                .enabled(false)
                .authority("USER")
                .build();

        checkIfUserAlreadyExists(desiredUsername);
        decorateUserRepository.save(user);
        log.info("-------------     New user saved to the db; user name: " + registerRequest.getUsername() + "     -------------");

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);

        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }


    private void checkIfUserAlreadyExists(String desiredUsername) {
        decorateUserRepository.findByUsername(desiredUsername)
                .ifPresent(alreadyExists -> {
                    throw new DecorateBackendException("User whit this name " + desiredUsername + " already exists");
                });
    }

    private String generateVerificationToken(DecorateUser user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    private String generateUniqueUserName(String userName) {
        String uniqueTag = UUID.randomUUID().toString();
        String uniqueUserName = userName + "<UNIQUE>" + uniqueTag;
        return uniqueUserName;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        enableUser(verificationToken.orElseThrow(() -> new DecorateBackendException("Invalid Token")));
    }

    private void enableUser(VerificationToken verificationToken) {
        Long userId = verificationToken.getUser().getUserId();
        String username = verificationToken.getUser().getUsername();
        DecorateUser user = decorateUserRepository.findByUserId(userId).orElseThrow(() -> new DecorateBackendException("User not found with name - " + username));
        user.setEnabled(true);
        decorateUserRepository.save(user);
    }
}
