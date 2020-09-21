package com.example.decorate.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDto {

    private String lastname;
    private String firstname;
    private String email;
}
