package com.example.api_inventario.Service;

import java.util.List;

import com.example.api_inventario.DTO.SerieHistDTOs.getSerieHistDTO;
import com.example.api_inventario.DTO.SerieHistDTOs.putSerieHistDTO;
import com.example.api_inventario.DTO.SerieHistDTOs.saveSerieHistDTO;

public interface SerieHistService {

    // Metodos Get
    public List<getSerieHistDTO> getAllSerieHist();

    public getSerieHistDTO getSerieHistById(Long id_serie_hist);
    // Fin Metodos Get

    public getSerieHistDTO saveSerieHist(saveSerieHistDTO serieDto);

    public getSerieHistDTO putSerieHist(Long id_serie_hist, putSerieHistDTO serieDto);

    public void deleteSerieHist(Long id_serie_hist);
}
