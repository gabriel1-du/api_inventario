package com.example.api_inventario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_inventario.Model.TipoHist;
import com.example.api_inventario.Service.TipoHistService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/tipoHistRequest")
@AllArgsConstructor
public class TipoHIstController {

    @Autowired 
    private TipoHistService tipoService;

    @PostMapping("/")
    public ResponseEntity<?> saveTipoHist(@RequestBody TipoHist tipo){

        try{

            // Se guarda el tipo dentro del servicio
            tipoService.saveTipoHist(tipo);
            return ResponseEntity.ok(tipo);

        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    };


}
