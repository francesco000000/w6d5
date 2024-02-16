package it.epicode.w6d5.service;

import it.epicode.w6d5.exception.NotFoundException;
import it.epicode.w6d5.model.Dipendente;
import it.epicode.w6d5.model.Dispositivo;
import it.epicode.w6d5.modelRequest.DispositivoRequest;
import it.epicode.w6d5.repository.DipendenteRepository;
import it.epicode.w6d5.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private DipendenteService dipendenteService;

    public Page<Dispositivo> getAllDispositivo(Pageable pageable){
        return dispositivoRepository.findAll(pageable);
    }
    public Dispositivo lookinForId (int id)throws NotFoundException{
        return dispositivoRepository.findById(id).
                orElseThrow(()->new NotFoundException("BlogPost con id="+id+" non trovato"));
    }
    public Dispositivo saveDispositivo (DispositivoRequest dispositivoRequest) throws NotFoundException{

        Dispositivo dispositivo = new Dispositivo(dispositivoRequest.getTipoDispositivo(),dispositivoRequest.getStatoDispositivo());
        return dispositivoRepository.save(dispositivo);
    }
    public Dispositivo updateDispositivo (int id,DispositivoRequest dispositivoRequest)throws NotFoundException{
        Dispositivo dispositivo= lookinForId(id);
        dispositivo.setStatoDispositivo(dispositivoRequest.getStatoDispositivo());
        dispositivo.setTipoDispositivo(dispositivoRequest.getTipoDispositivo());

        return dispositivoRepository.save(dispositivo);
    }
    public void deleteDispositivo(int id)throws NotFoundException{
        Dispositivo dispositivo= lookinForId(id);
        dispositivoRepository.delete(dispositivo);
    }
    public Dispositivo uploadDipendente(int id,int dipendenteId){
       Dispositivo dispositivo = lookinForId(id);
       Dipendente dipendente = dipendenteService.lookingForId(dipendenteId);
       dispositivo.setDipendente(dipendente);
       return dispositivoRepository.save(dispositivo);
    }


}
