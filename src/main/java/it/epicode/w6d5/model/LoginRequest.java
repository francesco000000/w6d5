package it.epicode.w6d5.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Email(message = "Email obbligatoria")
    private String email;
    @NotBlank(message = "username obbligatorio")
    private String password;
}
