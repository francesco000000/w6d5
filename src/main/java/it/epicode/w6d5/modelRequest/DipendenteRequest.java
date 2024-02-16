package it.epicode.w6d5.modelRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DipendenteRequest {
    private String userName;
    @NotNull(message = "Nome obbligatorio")
    @NotEmpty(message = "Nome obbligatorio")
    private String nome;
    @NotNull(message = "Cognome obbligatorio")
    @NotEmpty(message = "cognome obbligatorio")
    private String cognome;
    @Email(message = "Email obbligatoria")
    private String email;

}
