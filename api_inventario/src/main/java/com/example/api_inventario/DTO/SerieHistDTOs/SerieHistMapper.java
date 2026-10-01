package com.example.api_inventario.DTO.SerieHistDTOs;

import org.springframework.stereotype.Component;

import com.example.api_inventario.Model.AutorHist;
import com.example.api_inventario.Model.GeneroHist;
import com.example.api_inventario.Model.SerieHist;
import com.example.api_inventario.Model.TipoHist;
import com.example.api_inventario.Repository.AutorHistRepository;
import com.example.api_inventario.Repository.GeneroHistRepository;
import com.example.api_inventario.Repository.TipoHistRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SerieHistMapper {

    private final AutorHistRepository autorRepo;
    private final GeneroHistRepository generoRepo;
    private final TipoHistRepository tipoRepo;

    //Para get
    public getSerieHistDTO toGetDTO(SerieHist serie) {
        if (serie == null) {
            return null;
        }

        getSerieHistDTO dto = new getSerieHistDTO();
        dto.setId_serie_hist(serie.getId_serie_hist());
        dto.setNombre_serie_hist(serie.getNombre_serie_hist());

        if (serie.getAutor() != null) {
            dto.setId_autor(serie.getAutor().getId_autor());
            dto.setNombre_autor(serie.getAutor().getNombre_autor());
        }

        if (serie.getGenero() != null) {
            dto.setId_genero(serie.getGenero().getId_genero());
            dto.setNombre_genero(serie.getGenero().getNombre_genero());
        }

        if (serie.getTipoHist() != null) {
            dto.setId_tipo_hist(serie.getTipoHist().getId_tipo_hist());
            dto.setNombre_tipo_hist(serie.getTipoHist().getNombre_tipo());
        }

        return dto;
    }

    //para post
    public SerieHist toEntityFromSaveDTO(saveSerieHistDTO dto) {
        if (dto == null) {
            return null;
        }

        SerieHist serie = new SerieHist();
        serie.setNombre_serie_hist(dto.getNombre_serie_hist());

        // Verificación de relaciones FK existentes
        AutorHist autor = autorRepo.findById(dto.getId_autor())
                .orElseThrow(() -> new RuntimeException("ID de autor inexistente: " + dto.getId_autor()));
        serie.setAutor(autor);

        GeneroHist genero = generoRepo.findById(dto.getId_genero())
                .orElseThrow(() -> new RuntimeException("ID de género inexistente: " + dto.getId_genero()));
        serie.setGenero(genero);

        TipoHist tipo = tipoRepo.findById(dto.getId_tipo_hist())
                .orElseThrow(() -> new RuntimeException("ID de tipo de historieta inexistente: " + dto.getId_tipo_hist()));
        serie.setTipoHist(tipo);

        return serie;
    }

    //Para put
    public void updateEntityFromPutDTO(putSerieHistDTO dto, SerieHist serieExistente) {
        if (dto == null || serieExistente == null) {
            return;
        }

        if (dto.getNombre_serie_hist() != null) {
            serieExistente.setNombre_serie_hist(dto.getNombre_serie_hist());
        }
    }

}
