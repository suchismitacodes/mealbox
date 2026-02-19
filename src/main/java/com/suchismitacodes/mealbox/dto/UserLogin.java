package com.suchismitacodes.mealbox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserLogin {

    private String userEmail;
    private String userPassword;
}
