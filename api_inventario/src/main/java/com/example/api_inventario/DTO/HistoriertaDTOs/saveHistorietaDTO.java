package com.example.api_inventario.DTO.HistoriertaDTOs;

import java.math.BigDecimal;


import lombok.Data;

@Data 
public class saveHistorietaDTO {

    //Atributos propios de la hist
    private Long id_historieta;

    private String nombre_historieta;

    private String descripcion_historieta;

    private String portada;

    private Integer numero_historieta;

    private BigDecimal precio;

    //Fin Atirbutos propios


    //Atributos FK (foreign key)
    private Long id_genero;

    private Long id_tipo_hist;

    private Long id_autor;

    private Long id_serie_hist;
}
