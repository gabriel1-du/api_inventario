package com.example.api_inventario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_inventario.DTO.SerieHistDTOs.getSerieHistDTO;
import com.example.api_inventario.DTO.SerieHistDTOs.putSerieHistDTO;
import com.example.api_inventario.DTO.SerieHistDTOs.saveSerieHistDTO;
import com.example.api_inventario.Service.SerieHistService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/serieHistRequest")
@AllArgsConstructor 
public class SerieHistController {

    @Autowired 
    private SerieHistService serieService;

    @GetMapping("/{id_serie_hist}")
    public ResponseEntity<?> getSerieHistById(@PathVariable("id_serie_hist") Long id_serie_hist) {
        try {
            getSerieHistDTO serie = serieService.getSerieHistById(id_serie_hist);
            return ResponseEntity.ok(serie);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    };


    // Metodos get
    @GetMapping("/")
    public ResponseEntity<List<getSerieHistDTO>> getAllSerieHist() {
        List<getSerieHistDTO> series = serieService.getAllSerieHist();
        return ResponseEntity.ok(series);
    }
    
    @PostMapping("/")
    public ResponseEntity<?> saveSerieHist(@RequestBody saveSerieHistDTO serie) {
        try {
            getSerieHistDTO nuevaSerie = serieService.saveSerieHist(serie);
            return ResponseEntity.ok(nuevaSerie);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    };


    @PutMapping("/{id_serie_hist}")
    public ResponseEntity<?> putSerieHist(@PathVariable("id_serie_hist") Long id_serie_hist, @RequestBody putSerieHistDTO serie) {
        try {
            getSerieHistDTO serieActualizada = serieService.putSerieHist(id_serie_hist, serie);
            return ResponseEntity.ok(serieActualizada);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    };

    @DeleteMapping("/{id_serie_hist}")
    public ResponseEntity<?> deleteSerieHist(@PathVariable("id_serie_hist") Long id_serie_hist) {
        try {
            serieService.deleteSerieHist(id_serie_hist);
            return ResponseEntity.ok("Serie eliminada exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
}
