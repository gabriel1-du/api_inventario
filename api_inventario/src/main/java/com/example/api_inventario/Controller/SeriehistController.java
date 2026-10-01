package com.example.api_inventario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_inventario.Model.GeneroHist;
import com.example.api_inventario.Model.SerieHist;
import com.example.api_inventario.Service.SerieHistService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/serieHistRequest")
@AllArgsConstructor 
public class SeriehistController {

    @Autowired 
    private SerieHistService serv;

    @PostMapping("/")
    public ResponseEntity<?> saveSerieHist(@RequestBody SerieHist serie){

        try{

            //Se guarda el autor dentro del servicio
            serv.saveSerieHist(serie);
            return ResponseEntity.ok(serie);

        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    };


}
