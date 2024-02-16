package it.epicode.w6d5.modelRequest;

import it.epicode.w6d5.model.Dipendente;
import it.epicode.w6d5.model.StatoDispositivo;
import it.epicode.w6d5.model.TipoDispositivo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoRequest {
    @NotNull(message = "contenuto obbligatorio")
    private TipoDispositivo tipoDispositivo;
    @NotNull(message = "contenuto obbligatorio")
    private StatoDispositivo statoDispositivo;
    private Integer dipendenteId;
}
