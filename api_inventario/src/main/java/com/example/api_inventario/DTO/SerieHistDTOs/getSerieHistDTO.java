package com.example.api_inventario.DTO.SerieHistDTOs;

import lombok.Data;

@Data 
public class getSerieHistDTO {

    // Atributos propios de SerieHist
    private Long id_serie_hist;

    private String nombre_serie_hist;

    // Atributos FK y sus nombres correspondientes
    private Long id_autor;

    private String nombre_autor;

    private Long id_genero;

    private String nombre_genero;

    private Long id_tipo_hist;

    private String nombre_tipo_hist;

}
