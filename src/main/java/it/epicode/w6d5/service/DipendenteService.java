package it.epicode.w6d5.service;

import it.epicode.w6d5.exception.NotFoundException;
import it.epicode.w6d5.model.Dipendente;
import it.epicode.w6d5.modelRequest.DipendenteRequest;
import it.epicode.w6d5.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Page<Dipendente> getAllDipendenti(Pageable pageable){
        return dipendenteRepository.findAll(pageable);
    }
    public Dipendente lookingForId(int id)throws NotFoundException{
        return dipendenteRepository.findById(id).
                orElseThrow(()->new NotFoundException("Autore con id="+ id + " non trovato"));
    }
    public Dipendente saveDipendente(DipendenteRequest dipendenteRequest){
        Dipendente dipendente= new Dipendente(dipendenteRequest.getUserName(),dipendenteRequest.getNome(),dipendenteRequest.getCognome(),dipendenteRequest.getEmail());
        sendMail(dipendente.getEmail());
        return dipendenteRepository.save(dipendente);
    }
    public Dipendente uploadDipendente(int id,DipendenteRequest dipendenteRequest)throws NotFoundException{
     Dipendente dipendente= lookingForId(id);
     dipendente.setUserName(dipendenteRequest.getUserName());
     dipendente.setNome(dipendenteRequest.getNome());
     dipendente.setCognome(dipendenteRequest.getCognome());
     dipendente.setEmail(dipendenteRequest.getEmail());
     return dipendenteRepository.save(dipendente);

    }
    public void deleteDipendente(int id)throws NotFoundException{
        Dipendente dipendente = lookingForId(id);
        dipendenteRepository.delete(dipendente);
    }
    public Dipendente uploadImgProfilo(int id,String url)throws NotFoundException{
        Dipendente dipendente= lookingForId(id);
        dipendente.setImmagineProfilo(url);
        return dipendenteRepository.save(dipendente);
    }
    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }
}
