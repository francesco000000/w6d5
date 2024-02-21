package it.epicode.w6d5.modelRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteRequest {
    private String nome;
    @Email(message = "email obbligatoria")
    private String email;
    @NotBlank(message = "username obbligatorio")
    private String password;

}
