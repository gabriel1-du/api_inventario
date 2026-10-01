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

import com.example.api_inventario.Model.AutorHist;
import com.example.api_inventario.Service.AutorHistService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/autorHistRequest")
@AllArgsConstructor 
public class AutorHistController {

    @Autowired 
    private AutorHistService autorService;


    //Metodos get
    @GetMapping("/")
    public ResponseEntity<List<AutorHist>> getAllAutorHist (){
        
        //guardamos autores dentro de una lista
        List<AutorHist> autores = autorService.getAllAutorHist();
        return ResponseEntity.ok(autores); 

    }


    @GetMapping("/{id_autor}")
    public ResponseEntity<?> getAutorHistById (@PathVariable("id_autor") Long id_autor){

        try {
            AutorHist autor_encontrado = autorService.getAutorHistById(id_autor);
            return ResponseEntity.ok(autor_encontrado);
        } catch (Exception e) { 

            //Retorna la excepcion
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    };

    // Fin metodos get

    
    @PostMapping("/")
    public ResponseEntity<?> postAutorHist(@RequestBody AutorHist autor_nuevo){

        try{

            //Se guarda el autor dentro del servicio
            autorService.saveAutorHist(autor_nuevo);
            return ResponseEntity.ok(autor_nuevo);

        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    };

    @PutMapping("/{id_autor}")
    public ResponseEntity<?> putAutorHist(@PathVariable("id_autor") Long id_autor , @RequestBody AutorHist autor){

        try {
                
            AutorHist autor_actualizado = autorService.putAutorHist(autor, id_autor );
            
            return ResponseEntity.ok(autor_actualizado);

        }catch (Exception e){
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    };

    @DeleteMapping("/{id_autor}")
    public ResponseEntity<?> deleteAutorHist(@PathVariable("id_autor") Long id_autor){
        
        try{

            autorService.deleteAutorHist(id_autor);
            
            return ResponseEntity.ok("Autor eliminado exitosamente");

        } catch(Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    };

    
}
