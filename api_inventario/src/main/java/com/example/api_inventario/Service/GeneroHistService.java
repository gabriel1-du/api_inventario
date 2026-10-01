package com.example.api_inventario.Service;

import java.util.List;

import com.example.api_inventario.Model.GeneroHist;

public interface GeneroHistService {

    //Metodos Get
    public List<GeneroHist> getAllGeneroHist(); 

    public GeneroHist getGeneroHistbyId(Long id_genero);
    //Fin Metodos Get

    public GeneroHist saveGeneroHist(GeneroHist genero);

    public GeneroHist putGeneroHist(Long id_genero , GeneroHist genero);

    public void deleteGeneroHist(Long id_genero);

}
