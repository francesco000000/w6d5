package it.epicode.w6d5.controller;

import com.cloudinary.Cloudinary;
import it.epicode.w6d5.exception.BadRequestException;
import it.epicode.w6d5.model.Dipendente;
import it.epicode.w6d5.model.Dispositivo;
import it.epicode.w6d5.model.StatoDispositivo;
import it.epicode.w6d5.modelRequest.DipendenteRequest;
import it.epicode.w6d5.modelRequest.DispositivoRequest;
import it.epicode.w6d5.service.DipendenteService;
import it.epicode.w6d5.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
@RestController
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("/dispositivi")
    public Page<Dispositivo> getAll(Pageable pageable){
        return dispositivoService.getAllDispositivo(pageable);
    }
    @GetMapping("/dispositivi/{id}")
    public Dispositivo getDispositivoById(@PathVariable int id)  {
        return dispositivoService.lookinForId(id);

    }
    @PostMapping("/dispositivi")
    public Dispositivo saveDispositivo(@RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dispositivoService.saveDispositivo(dispositivoRequest) ;
    }
    @PutMapping("/dispositivi/{id}")
    public Dispositivo updateDispositivo(@PathVariable int id, @RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return dispositivoService.saveDispositivo(dispositivoRequest) ;
    }
    @PatchMapping("/dispositivi/{id}/upload")
    public Dispositivo uploadDipendente(@PathVariable int id, @RequestParam("dipendenteId") int dipendenteId ) throws Exception {
         Dispositivo dispositivo = dispositivoService.lookinForId(id);
         if (dispositivo.getStatoDispositivo().equals(StatoDispositivo.MANUTENZIONE)|| equals(StatoDispositivo.ASSEGNATO)){
             throw new Exception("Dispositivo non disponibile");
         }else {
             return  dispositivoService.uploadDipendente(id, dipendenteId);
         }
    }
    @DeleteMapping("/dispositivi/{id}")
    public void deleteDispositivo(@PathVariable int id){
       dispositivoService.deleteDispositivo(id);
    }
}
