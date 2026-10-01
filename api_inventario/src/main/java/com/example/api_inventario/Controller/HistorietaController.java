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

import com.example.api_inventario.DTO.HistoriertaDTOs.getHistorietaDTO;
import com.example.api_inventario.DTO.HistoriertaDTOs.putHistorietaDTO;
import com.example.api_inventario.DTO.HistoriertaDTOs.saveHistorietaDTO;
import com.example.api_inventario.Service.HistorietaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/historietaRequest")
@AllArgsConstructor
public class HistorietaController {

    @Autowired
    private HistorietaService historietaService;

    // Metodos get
    @GetMapping("/")
    public ResponseEntity<List<getHistorietaDTO>> getAllHistorieta() {
        List<getHistorietaDTO> historietas = historietaService.getAllHistorieta();
        return ResponseEntity.ok(historietas);
    }

    @GetMapping("/{id_historieta}")
    public ResponseEntity<?> getHistorietaById(@PathVariable("id_historieta") Long id_historieta) {
        try {
            getHistorietaDTO historieta = historietaService.getHistorietaById(id_historieta);
            return ResponseEntity.ok(historieta);

        } catch (Exception e) {
            // Retorna la excepcion 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    };

    @PostMapping("/")
    public ResponseEntity<?> saveHistorieta(@RequestBody saveHistorietaDTO historieta) {
        try {
            getHistorietaDTO nuevaHistorieta = historietaService.saveHistorieta(historieta);
            return ResponseEntity.ok(nuevaHistorieta);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    };

    @PutMapping("/{id_historieta}")
    public ResponseEntity<?> putHistorieta(@PathVariable("id_historieta") Long id_historieta, @RequestBody putHistorietaDTO historieta) {
        try {
            getHistorietaDTO historietaActualizada = historietaService.putHistorieta(id_historieta, historieta);
            return ResponseEntity.ok(historietaActualizada);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    };

    @DeleteMapping("/{id_historieta}")
    public ResponseEntity<?> deleteHistorieta(@PathVariable("id_historieta") Long id_historieta) {
        try {
            historietaService.deleteHistorieta(id_historieta);
            return ResponseEntity.ok("Historieta eliminada exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
