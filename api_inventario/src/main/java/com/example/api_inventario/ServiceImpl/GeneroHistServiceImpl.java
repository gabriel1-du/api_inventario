package com.example.api_inventario.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.api_inventario.Model.GeneroHist;
import com.example.api_inventario.Repository.GeneroHistRepository;
import com.example.api_inventario.Service.GeneroHistService;


import lombok.RequiredArgsConstructor;


@Service 
@RequiredArgsConstructor 
public class GeneroHistServiceImpl implements  GeneroHistService {

    @Autowired 
    private final GeneroHistRepository generoRepo;


    //Metodos get
    public List<GeneroHist> getAllGeneroHist(){
        return generoRepo.findAll();
    }; 

    public GeneroHist getGeneroHistbyId(Long id_genero){

        return generoRepo.findById(id_genero)
            .orElseThrow(() -> new RuntimeException("GeneroHist no encontrada con id: " + id_genero));

    };
    //Fin metodos get



    public GeneroHist saveGeneroHist(GeneroHist genero){

        try {   
            GeneroHist generoNuevo = generoRepo.save(genero);

            return generoNuevo;
            
        } catch (Exception e) {
            
            throw new RuntimeException("Error al guardar el Genero" + e);
        }

    };

    public GeneroHist putGeneroHist(Long id_genero , GeneroHist genero){

        GeneroHist generoExistente = generoRepo.findById(id_genero)
                .orElseThrow(() -> new RuntimeException("Genero no econtrado con el ID" + id_genero));
            
        if (generoExistente.getNombre_genero() != null) {
            generoExistente.setNombre_genero(genero.getNombre_genero());

        };

        return generoRepo.save(generoExistente);
            
    };

    public void deleteGeneroHist(Long id_genero){

        
        GeneroHist genero_del = generoRepo.findById(id_genero)
            .orElseThrow(() -> new RuntimeException("Genero no encontrado con el ID" + id_genero));

        generoRepo.delete(genero_del);
    };
    

}
