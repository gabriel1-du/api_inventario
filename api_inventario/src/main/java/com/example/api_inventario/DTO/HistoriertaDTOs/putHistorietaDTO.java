package com.example.api_inventario.DTO.HistoriertaDTOs;

import java.math.BigDecimal;

import lombok.Data;

@Data 
public class putHistorietaDTO {

    //Este no lleva ningun id , porque no se pude cambiar logicamente

    private String nombre_historieta;

    private String descripcion_historieta;

    private String portada;

    private Integer numero_historieta;

    private BigDecimal precio;

    
    
}
