package com.example.api_inventario.DTO.SerieHistDTOs;

import lombok.Data;

@Data 
public class putSerieHistDTO {

    // No incluye IDs ya que solo actualiza los datos propios editables
    private String nombre_serie_hist;
    
}
