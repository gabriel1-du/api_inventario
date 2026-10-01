package com.example.api_inventario.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_inventario.Model.SerieHist;
import com.example.api_inventario.Repository.SerieHistRepository;
import com.example.api_inventario.Service.SerieHistService;

@Service 
public class SerieHistServiceImpl implements  SerieHistService {

    @Autowired 
    private SerieHistRepository repo;


    //Metodos get
    public List<SerieHist> getAllSerieHist(){
        return repo.findAll();
    }; 

    public SerieHist getSerieHistById (Long id_serie){

        return repo.findById(id_serie)
            .orElseThrow(() -> new RuntimeException("Serie Hist no encontrada con id: " + id_serie));        
    };
    //Fin metodos get


    
    public SerieHist saveSerieHist(SerieHist serie){

        try {   
            SerieHist n_serie = repo.save(serie);

            return n_serie;
            
        } catch (Exception e) {
            
            throw new RuntimeException("Error al guardar la Serie Hist" + e);
        }

    };


     public SerieHist putSerieHist(SerieHist serie, Long id_serie){

        SerieHist serieExistente = repo.findById(id_serie)
                .orElseThrow(() -> new RuntimeException("Serie Hist no econtrado con el ID" + id_serie));
            
        if (serieExistente.getNombre_serie_hist() != null) {
            serieExistente.setNombre_serie_hist(serie.getNombre_serie_hist());

        };

        return repo.save(serieExistente);
            
    };

    public void deleteSerieHist(Long id){

        
        SerieHist serie_del = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Serie Hist no encontrado con el ID" + id));

        repo.delete(serie_del);
    };


}
