package com.example.api_inventario.DTO.HistoriertaDTOs;

import org.springframework.stereotype.Component;

import com.example.api_inventario.Model.AutorHist;
import com.example.api_inventario.Model.GeneroHist;
import com.example.api_inventario.Model.Historieta;
import com.example.api_inventario.Model.SerieHist;
import com.example.api_inventario.Model.TipoHist;
import com.example.api_inventario.Repository.AutorHistRepository;
import com.example.api_inventario.Repository.GeneroHistRepository;
import com.example.api_inventario.Repository.SerieHistRepository;
import com.example.api_inventario.Repository.TipoHistRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HistorietaMapper {

    // repos
    private final AutorHistRepository autorRepo;
    private final GeneroHistRepository generoRepo;
    private final TipoHistRepository tipoRepo;
    private final SerieHistRepository serieRepo;
    //fin repos

    
    //para lls metodos get
    public getHistorietaDTO toGetDTO(Historieta historieta) {
        if (historieta == null) {
            return null;
        }

        getHistorietaDTO dto = new getHistorietaDTO();
        
        // Atributos propios
        dto.setId_historieta(historieta.getId_historieta());
        dto.setNombre_historieta(historieta.getNombre_historieta());
        dto.setDescripcion_historieta(historieta.getDescripcion_historieta());
        dto.setPortada(historieta.getPortada());
        dto.setNumero_historieta(historieta.getNumero_historieta());
        dto.setPrecio(historieta.getPrecio());

        // Atributos FK
        if (historieta.getAutor() != null) {
            dto.setId_autor(historieta.getAutor().getId_autor());
            dto.setNombre_autor(historieta.getAutor().getNombre_autor());
        }
        
        if (historieta.getGenero() != null) {
            dto.setId_genero(historieta.getGenero().getId_genero());
            dto.setNombre_genero(historieta.getGenero().getNombre_genero());
        }

        if (historieta.getTipoHist() != null) {
            dto.setId_tipo_hist(historieta.getTipoHist().getId_tipo_hist());
            dto.setNombre_tipo_hist(historieta.getTipoHist().getNombre_tipo());
        }

        if (historieta.getSerieHist() != null) {
            dto.setId_serie_hist(historieta.getSerieHist().getId_serie_hist());
            dto.setSerie_hist_nombre(historieta.getSerieHist().getNombre_serie_hist());
        }

        return dto;
    }


    //para el metodo post
    public Historieta toEntityFromSaveDTO(saveHistorietaDTO dto) {
        if (dto == null) {
            return null;
        }

        Historieta historieta = new Historieta();
        
        // Atributos propios
        historieta.setNombre_historieta(dto.getNombre_historieta());
        historieta.setDescripcion_historieta(dto.getDescripcion_historieta());
        historieta.setPortada(dto.getPortada());
        historieta.setNumero_historieta(dto.getNumero_historieta());
        historieta.setPrecio(dto.getPrecio());

        // Verificación e inyección de Entidades FK
        
        // 1. Verificar Autor
        AutorHist autor = autorRepo.findById(dto.getId_autor())
            .orElseThrow(() -> new RuntimeException("ID de autor inexistente: " + dto.getId_autor()));
        historieta.setAutor(autor);

        // 2. Verificar Genero
        GeneroHist genero = generoRepo.findById(dto.getId_genero())
            .orElseThrow(() -> new RuntimeException("ID de género inexistente: " + dto.getId_genero()));
        historieta.setGenero(genero);

        // 3. Verificar Tipo
        TipoHist tipo = tipoRepo.findById(dto.getId_tipo_hist())
            .orElseThrow(() -> new RuntimeException("ID de tipo de historieta inexistente: " + dto.getId_tipo_hist()));
        historieta.setTipoHist(tipo);

        // 4. Verificar Serie
        SerieHist serie = serieRepo.findById(dto.getId_serie_hist())
            .orElseThrow(() -> new RuntimeException("ID de serie inexistente: " + dto.getId_serie_hist()));
        historieta.setSerieHist(serie);

        return historieta;
    }

    //Para el metodo update
    public void updateEntityFromPutDTO(putHistorietaDTO dto, Historieta historietaExistente) {
        if (dto == null || historietaExistente == null) {
            return;
        }

        if (dto.getNombre_historieta() != null) {
            historietaExistente.setNombre_historieta(dto.getNombre_historieta());
        }
        
        if (dto.getDescripcion_historieta() != null) {
            historietaExistente.setDescripcion_historieta(dto.getDescripcion_historieta());
        }

        if (dto.getPortada() != null) {
            historietaExistente.setPortada(dto.getPortada());
        }

        if (dto.getNumero_historieta() != null) {
            historietaExistente.setNumero_historieta(dto.getNumero_historieta());
        }

        if (dto.getPrecio() != null) {
            historietaExistente.setPrecio(dto.getPrecio());
        }
    }

}
