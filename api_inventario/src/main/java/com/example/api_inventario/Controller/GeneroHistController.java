package com.example.api_inventario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.api_inventario.Model.GeneroHist;
import com.example.api_inventario.Service.GeneroHistService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController 
@RequestMapping("/api/generoHistRequest")
@AllArgsConstructor 
public class GeneroHistController {

    @Autowired 
    private GeneroHistService generoService;
    

    //Metodos get
    @GetMapping("/")
    public ResponseEntity<List<GeneroHist>> getAllGeneroHist() {
        
        List<GeneroHist> generos = generoService.getAllGeneroHist();

        return ResponseEntity.ok(generos);
    }  

    @GetMapping("/{id_genero}")
    public ResponseEntity<?> getGeneroHistById (@PathVariable("id_genero") Long id_genero){

        try {
            GeneroHist genero = generoService.getGeneroHistbyId(id_genero);
            return ResponseEntity.ok(genero);

        } catch (Exception e) { 

            //Retorna la excepcion
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    };

    

    @PostMapping("/")
    public ResponseEntity<?> saveGeneroHist(@RequestBody GeneroHist genero){

        try{

            //Se guarda el autor dentro del servicio
            generoService.saveGeneroHist(genero);
            return ResponseEntity.ok(genero);

        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    };

    @PutMapping("/{id_genero}")
    public ResponseEntity<?> putGeneroHist(@PathVariable("id_genero") Long id_genero , @RequestBody GeneroHist genero){

        try {
                
            GeneroHist genero_actualizado = generoService.putGeneroHist(id_genero, genero);
            
            return ResponseEntity.ok(genero_actualizado);

        }catch (Exception e){
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    };

    @DeleteMapping("{id_genero}")
    public ResponseEntity<?> deleteGeneroHist(@PathVariable("id_genero") Long id_genero){
        try{

            generoService.deleteGeneroHist(id_genero);
            
            return ResponseEntity.ok("Genero eliminado exitosamente");

        } catch(Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
