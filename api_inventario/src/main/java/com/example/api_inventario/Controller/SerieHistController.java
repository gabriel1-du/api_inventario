package com.example.api_inventario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_inventario.DTO.SerieHistDTOs.getSerieHistDTO;
import com.example.api_inventario.DTO.SerieHistDTOs.saveSerieHistDTO;
import com.example.api_inventario.Service.SerieHistService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/serieHistRequest")
@AllArgsConstructor 
public class SerieHistController {

    @Autowired 
    private SerieHistService serieService;


    // Metodos get
    @GetMapping("/")
    public ResponseEntity<List<getSerieHistDTO>> getAllSerieHist() {
        List<getSerieHistDTO> series = serieService.getAllSerieHist();
        return ResponseEntity.ok(series);
    }
    
    @PostMapping("/post")
    public ResponseEntity<?> saveSerieHist(@RequestBody saveSerieHistDTO serie) {
        try {
            getSerieHistDTO nuevaSerie = serieService.saveSerieHist(serie);
            return ResponseEntity.ok(nuevaSerie);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    };

    
}
