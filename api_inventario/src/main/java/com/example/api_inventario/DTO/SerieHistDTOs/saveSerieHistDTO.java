package com.example.api_inventario.DTO.SerieHistDTOs;

import lombok.Data;

@Data 
public class saveSerieHistDTO {

    // Atributo propio
    private String nombre_serie_hist;

    // Atributos FK necesarios para crear la serie
    private Long id_autor;

    private Long id_genero;

    private Long id_tipo_hist;

}
