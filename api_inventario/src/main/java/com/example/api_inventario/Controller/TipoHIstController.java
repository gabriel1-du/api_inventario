package com.example.api_inventario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id_tipo_hist}")
    public ResponseEntity<?> getTipoHistById (@PathVariable("id_tipo_hist") Long id_tipo_hist){

        try {
            TipoHist tipo = tipoService.getTipoHistById(id_tipo_hist);
            return ResponseEntity.ok(tipo);

        } catch (Exception e) { 

            // Retorna la excepcion
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    };

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
