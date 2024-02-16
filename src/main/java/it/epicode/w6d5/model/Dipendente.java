package it.epicode.w6d5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String nome;
    private String cognome;
    private String email;
    private String immagineProfilo;
    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> dispositivi;

    public Dipendente(String userName, String nome, String cognome, String email) {
        this.userName = userName;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public Dipendente() {
    }
}
