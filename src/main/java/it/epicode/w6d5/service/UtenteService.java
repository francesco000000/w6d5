package it.epicode.w6d5.service;

import it.epicode.w6d5.exception.NotFoundException;
import it.epicode.w6d5.model.Role;
import it.epicode.w6d5.model.Utente;
import it.epicode.w6d5.modelRequest.UtenteRequest;
import it.epicode.w6d5.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UtenteRepository utenteRepository;
    public Utente save(UtenteRequest utenteRequest){

        Utente utente = new Utente();
        utente.setNome(utenteRequest.getNome());
        utente.setEmail(utenteRequest.getEmail());
        utente.setPassword(encoder.encode(utenteRequest.getPassword()));
        utente.setRole(Role.USER);

        return utenteRepository.save(utente);
    }
    public  Utente getUtenteById(int id){
        return utenteRepository.findById(id).orElseThrow(()->new NotFoundException("Utente non trovato"));
    }

          public Utente getUtenteByEmail(String email){
            return utenteRepository.findByEmail(email).orElseThrow(()->new NotFoundException("Username non trovato"));
        }
        public List<Utente> getAllUtenti (){
        return utenteRepository.findAll();
        }
        public Utente updateUtente (String email,UtenteRequest utenteRequest){
        Utente utente = getUtenteByEmail(email);
        utente.setNome(utenteRequest.getNome());
        utente.setEmail(utenteRequest.getEmail());
         utenteRepository.save(utente);
         return utente;
        }
        public Utente updateRole (String email,Role role){
        Utente utente = getUtenteByEmail(email);
        utente.setRole(role);
        utenteRepository.save(utente);
        return utente;
        }
        public void deleteUtente(String email){
        Utente utente= getUtenteByEmail(email);
        utenteRepository.delete(utente);
        }
}
