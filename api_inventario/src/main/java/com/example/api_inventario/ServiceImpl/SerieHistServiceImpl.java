package com.example.api_inventario.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_inventario.DTO.SerieHistDTOs.SerieHistMapper;
import com.example.api_inventario.DTO.SerieHistDTOs.getSerieHistDTO;
import com.example.api_inventario.DTO.SerieHistDTOs.putSerieHistDTO;
import com.example.api_inventario.DTO.SerieHistDTOs.saveSerieHistDTO;
import com.example.api_inventario.Model.SerieHist;
import com.example.api_inventario.Repository.SerieHistRepository;
import com.example.api_inventario.Service.SerieHistService;

@Service 
public class SerieHistServiceImpl implements  SerieHistService {

    @Autowired 
    private SerieHistRepository serieRepo;

    @Autowired
    private SerieHistMapper serieMapper;

    // Metodos get
    public List<getSerieHistDTO> getAllSerieHist() {
        return serieRepo.findAll()
                .stream()
                .map(serieMapper::toGetDTO)
                .collect(Collectors.toList());
    };

    public getSerieHistDTO getSerieHistById(Long id_serie_hist) {
        SerieHist serie = serieRepo.findById(id_serie_hist)
                .orElseThrow(() -> new RuntimeException("SerieHist no encontrada con id: " + id_serie_hist));

        return serieMapper.toGetDTO(serie);
    };
    // Fin metodos get

    public getSerieHistDTO saveSerieHist(saveSerieHistDTO serieDto) {
        try {
            SerieHist serieNueva = serieMapper.toEntityFromSaveDTO(serieDto);
            SerieHist serieGuardada = serieRepo.save(serieNueva);

            return serieMapper.toGetDTO(serieGuardada);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la SerieHist: " + e.getMessage());
        }
    };

    public getSerieHistDTO putSerieHist(Long id_serie_hist, putSerieHistDTO serieDto) {
        SerieHist serieExistente = serieRepo.findById(id_serie_hist)
                .orElseThrow(() -> new RuntimeException("SerieHist no encontrada con el ID: " + id_serie_hist));

        serieMapper.updateEntityFromPutDTO(serieDto, serieExistente);

        SerieHist serieActualizada = serieRepo.save(serieExistente);

        return serieMapper.toGetDTO(serieActualizada);
    };

    public void deleteSerieHist(Long id_serie_hist) {
        SerieHist serie_del = serieRepo.findById(id_serie_hist)
                .orElseThrow(() -> new RuntimeException("SerieHist no encontrada con el ID: " + id_serie_hist));

        serieRepo.delete(serie_del);
    };

}
