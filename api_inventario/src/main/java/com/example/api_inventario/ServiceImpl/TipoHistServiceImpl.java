package com.example.api_inventario.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_inventario.Model.TipoHist;
import com.example.api_inventario.Repository.TipoHistRepository;
import com.example.api_inventario.Service.TipoHistService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class TipoHistServiceImpl implements TipoHistService{

    @Autowired 
    private final TipoHistRepository tipoRepo;

    // Metodos get
    public List<TipoHist> getAllTipoHist(){
        return tipoRepo.findAll();
    }; 

    public TipoHist getTipoHistById(Long id_tipo_hist){

        return tipoRepo.findById(id_tipo_hist)
            .orElseThrow(() -> new RuntimeException("TipoHist no encontrada con id: " + id_tipo_hist));

    };
    // Fin metodos get

    public TipoHist saveTipoHist(TipoHist tipo){

        try {   
            TipoHist tipoNuevo = tipoRepo.save(tipo);

            return tipoNuevo;
            
        } catch (Exception e) {
            
            throw new RuntimeException("Error al guardar el Tipo de Historieta" + e);
        }

    };

    public TipoHist putTipoHist(Long id_tipo_hist, TipoHist tipo){

        TipoHist tipoExistente = tipoRepo.findById(id_tipo_hist)
                .orElseThrow(() -> new RuntimeException("Tipo Historieta no encontrado con el ID" + id_tipo_hist));
            
        if (tipoExistente.getNombre_tipo() != null) {
            tipoExistente.setNombre_tipo(tipo.getNombre_tipo());
        };

        return tipoRepo.save(tipoExistente);
            
    };

    public void deleteTipoHist(Long id_tipo_hist){
        
        TipoHist tipo_del = tipoRepo.findById(id_tipo_hist)
            .orElseThrow(() -> new RuntimeException("Tipo Historieta no encontrado con el ID" + id_tipo_hist));

        tipoRepo.delete(tipo_del);
    };



}
