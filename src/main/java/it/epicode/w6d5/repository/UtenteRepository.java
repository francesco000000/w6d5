package it.epicode.w6d5.repository;

import it.epicode.w6d5.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Integer> {
    public Optional<Utente> findByEmail(String email);
}
