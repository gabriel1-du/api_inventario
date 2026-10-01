package com.example.api_inventario.Service;

import java.util.List;

import com.example.api_inventario.Model.TipoHist;

public interface TipoHistService {
    

    // Metodos Get
    public List<TipoHist> getAllTipoHist(); 

    public TipoHist getTipoHistById(Long id_tipo_hist);
    // Fin Metodos Get

    public TipoHist saveTipoHist(TipoHist tipo);

    public TipoHist putTipoHist(Long id_tipo_hist, TipoHist tipo);

    public void deleteTipoHist(Long id_tipo_hist);
}
