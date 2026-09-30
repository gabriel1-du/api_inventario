package com.example.api_inventario.Service;

import java.util.List;

import com.example.api_inventario.Model.AutorHist;

public interface AutorHistService {

    //Metodos Crud
    public List<AutorHist> getAllAutorHist(); //trae todos los autores de una hist

    public AutorHist getAutorHistById(Long id_autorHist); //por id

    public AutorHist saveAutorHist(AutorHist autor); // guardar 

    public AutorHist putAutorHist(AutorHist autor, Long id_autorHist); //actualizar una autorHist

    public void deleteAutorHist(Long id);

}
