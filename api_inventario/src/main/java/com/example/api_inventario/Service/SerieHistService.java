package com.example.api_inventario.Service;

import java.util.List;


import com.example.api_inventario.Model.SerieHist;

public interface SerieHistService {

    public List<SerieHist> getAllSerieHist(); //trae todos los autores de una hist

    public SerieHist getSerieHistById(Long id_serierHist); //por id

    public SerieHist saveSerieHist(SerieHist serie); // guardar 

    public SerieHist putSerieHist(SerieHist serie, Long id_serie); //actualizar una autorHist

    public void deleteSerieHist(Long id);
}
