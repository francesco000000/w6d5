package it.epicode.w6d5.controller;

import it.epicode.w6d5.exception.BadRequestException;
import it.epicode.w6d5.exception.LoginFaultException;
import it.epicode.w6d5.model.LoginRequest;
import it.epicode.w6d5.model.Utente;
import it.epicode.w6d5.modelRequest.UtenteRequest;
import it.epicode.w6d5.security.JwtTools;
import it.epicode.w6d5.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JwtTools jwtTools;
    @PostMapping("/auth/register")
    public Utente register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.save(utenteRequest);
    }
    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        Utente utente = utenteService.getUtenteByEmail(loginRequest.getEmail());

       // if(encoder.matches(loginRequest.getPassword(), utente.getPassword())){
            return jwtTools.createToken(utente);
        }
       // else{
            //throw new LoginFaultException("username/password errate");
        }


