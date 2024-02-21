package it.epicode.w6d5.controller;

import it.epicode.w6d5.exception.BadRequestException;
import it.epicode.w6d5.model.Role;
import it.epicode.w6d5.model.Utente;
import it.epicode.w6d5.modelRequest.UtenteRequest;
import it.epicode.w6d5.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtenteController {

    @Autowired
    private UtenteService utenteService;
    @GetMapping("/utenti")
    public List<Utente> getAllUtenti (){
        return utenteService.getAllUtenti();
    }
    @GetMapping("/utenti/{email}")
    public Utente getUtenteEmail(@PathVariable String email){
        return utenteService.getUtenteByEmail(email);
    }
    @PostMapping("/utenti/")

    public Utente saveUtente (@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
          return utenteService.save(utenteRequest);
    }
    @PutMapping("/utenti/{email}")
    public Utente updateUtente (@PathVariable String email,@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return utenteService.updateUtente(email,utenteRequest);
    }
    @PatchMapping("/utenti/{email}/update")
    public Utente updateRole (@PathVariable String email,@RequestBody @Validated Role role){
        return utenteService.updateRole(email,role);
    }
    public void deleteUtente (String email){
       utenteService.deleteUtente(email);
    }
}

