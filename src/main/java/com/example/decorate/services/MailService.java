package com.example.decorate.services;

import com.example.decorate.domain.NotificationEmail;
import com.example.decorate.domain.dto.OrderDetails;
import com.example.decorate.exception.DecorateBackendException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;
    private SpringTemplateEngine templateEngine;

    @Async
    void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("textilgarden@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new DecorateBackendException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
    }

    public String sendEmailAboutOrder() throws MessagingException {
        Context context = new Context();
      //  context.setVariable("orderDetails", orderDetails);
      //  String process = templateEngine.process("emails/order", context);
        javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Megrendelés érkezett :");
        helper.setText("process,"   );
        helper.setTo("zsolt.preseka@yopmail.com");
        mailSender.send(mimeMessage);
        return "process";
    }
}
