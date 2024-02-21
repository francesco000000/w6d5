package it.epicode.w6d5.controller;

import com.cloudinary.Cloudinary;

import it.epicode.w6d5.exception.BadRequestException;
import it.epicode.w6d5.model.Dipendente;
import it.epicode.w6d5.model.Dispositivo;
import it.epicode.w6d5.modelRequest.DipendenteRequest;
import it.epicode.w6d5.modelRequest.DispositivoRequest;
import it.epicode.w6d5.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private Cloudinary cloudinary;
    @GetMapping("/dipendenti")
    public Page<Dipendente> getAll(Pageable pageable){
        return dipendenteService.getAllDipendenti(pageable);
    }
    @GetMapping("/dipendenti/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Dipendente getAutoreById(@PathVariable int id)  {
        return dipendenteService.lookingForId(id);

    }
    @PostMapping("/dipendenti")
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dipendenteService.saveDipendente(dipendenteRequest);
}
    @PutMapping("/dipendenti/{id}")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return dipendenteService.uploadDipendente(id,dipendenteRequest);
    }
    @PatchMapping("/dipendenti/{id}/uploadimg")
    public Dipendente uploadImg(@PathVariable int id, @RequestParam("uploadimg") MultipartFile file) throws IOException {
        return dipendenteService.uploadImgProfilo(id,
                (String) cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));

    }
    @DeleteMapping("/dipendente/{id}")
    public void deleteAutore(@PathVariable int id){
        dipendenteService.deleteDipendente(id);
    }
}
