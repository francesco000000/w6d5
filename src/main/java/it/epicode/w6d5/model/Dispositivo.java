package it.epicode.w6d5.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private TipoDispositivo tipoDispositivo;
    @Enumerated(EnumType.STRING)
    private StatoDispositivo statoDispositivo;
    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    public Dispositivo(TipoDispositivo tipoDispositivo, StatoDispositivo statoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
        this.statoDispositivo = statoDispositivo;

    }

    public Dispositivo() {
    }
}
