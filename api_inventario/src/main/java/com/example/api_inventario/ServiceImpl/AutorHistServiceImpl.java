package com.example.api_inventario.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_inventario.Model.AutorHist;
import com.example.api_inventario.Repository.AutorHistRepository;
import com.example.api_inventario.Service.AutorHistService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AutorHistServiceImpl implements AutorHistService{

    @Autowired 
    private final AutorHistRepository autorHistRepo;

    //Metodos get
    public List<AutorHist> getAllAutorHist(){
        return autorHistRepo.findAll();
    }; 


    public AutorHist getAutorHistById(Long id_autorHist){
          return autorHistRepo.findById(id_autorHist)
                .orElseThrow(() -> new RuntimeException("Autor hist no encontrada con id: " + id_autorHist));

    }; 

    //Fin metodos get

    //Metodos Post 
    public AutorHist saveAutorHist(AutorHist autor){

        try{ // Se trata de crear

            AutorHist nuevoAutor = autorHistRepo.save(autor);
            return nuevoAutor;

        } catch ( Exception e ) {
            
            throw new RuntimeException("Error al guardar al autor historico " + e);
        }

    };

    //Metodos put
    public AutorHist putAutorHist(AutorHist autor, Long id_autorHist){

        //Comprobacion de que exite el autor
        AutorHist autor_existente = autorHistRepo.findById(id_autorHist)
            .orElseThrow(() -> new RuntimeException("Autor no econtrado con el ID" + id_autorHist));

        // fin comprobacion

        //se guarda el nombre
        if (autor.getNombre_autor() != null) {
            autor_existente.setNombre_autor(autor.getNombre_autor());
        }

        return autorHistRepo.save(autor);

    }; 

    public void deleteAutorHist(Long id_autorHist){

        AutorHist autor_eliminado = autorHistRepo.findById(id_autorHist).
        orElseThrow(() -> new RuntimeException("Autor no encontrado con el ID" + id_autorHist));

        autorHistRepo.delete(autor_eliminado);
    };





}
