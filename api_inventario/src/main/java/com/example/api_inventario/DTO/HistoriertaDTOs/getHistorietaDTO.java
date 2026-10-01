package com.example.api_inventario.DTO.HistoriertaDTOs;

import java.math.BigDecimal;

import lombok.Data;

@Data 
public class getHistorietaDTO {

    //Atributos propios de la hist
    private Long id_historieta;

    private String nombre_historieta;

    private String descripcion_historieta;

    private String portada;

    private Integer numero_historieta;

    private BigDecimal precio;

    //Fin Atirbutos propios

    //Atribus fk (foreign key) y sus nombres en el registro
    private Long id_autor;

    private String nombre_autor;

    private Long id_genero;

    private String nombre_genero;

    private Long id_serie_hist;

    private String serie_hist_nombre;

    private Long id_tipo_hist;

    private String nombre_tipo_hist;

    

}
