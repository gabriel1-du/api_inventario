package com.example.api_inventario.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_inventario.DTO.HistoriertaDTOs.HistorietaMapper;
import com.example.api_inventario.DTO.HistoriertaDTOs.getHistorietaDTO;
import com.example.api_inventario.DTO.HistoriertaDTOs.putHistorietaDTO;
import com.example.api_inventario.DTO.HistoriertaDTOs.saveHistorietaDTO;
import com.example.api_inventario.Model.Historieta;
import com.example.api_inventario.Repository.HistorietaRepository;
import com.example.api_inventario.Service.HistorietaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistorietaServiceImpl implements HistorietaService {

    @Autowired
    private final HistorietaRepository historietaRepo;

    @Autowired
    private final HistorietaMapper historietaMapper;

    // Metodos get
    public List<getHistorietaDTO> getAllHistorieta() {
        return historietaRepo.findAll()
                .stream()
                .map(historietaMapper::toGetDTO)
                .collect(Collectors.toList());
    };

    public getHistorietaDTO getHistorietaById(Long id_historieta) {
        Historieta historieta = historietaRepo.findById(id_historieta)
                .orElseThrow(() -> new RuntimeException("Historieta no encontrada con id: " + id_historieta));

        return historietaMapper.toGetDTO(historieta);
    };
    // Fin metodos get



    public getHistorietaDTO saveHistorieta(saveHistorietaDTO historietaDto) {
        try {
            // El mapper valida las foreign keys y lanza la excepción si no existen
            Historieta historietaNueva = historietaMapper.toEntityFromSaveDTO(historietaDto);

            Historieta historietaGuardada = historietaRepo.save(historietaNueva);

            return historietaMapper.toGetDTO(historietaGuardada);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la Historieta: " + e.getMessage());
        }
    };



    public getHistorietaDTO putHistorieta(Long id_historieta, putHistorietaDTO historietaDto) {
        Historieta historietaExistente = historietaRepo.findById(id_historieta)
                .orElseThrow(() -> new RuntimeException("Historieta no encontrada con el ID: " + id_historieta));

        // Actualiza los campos permitidos usando el mapper
        historietaMapper.updateEntityFromPutDTO(historietaDto, historietaExistente);

        Historieta historietaActualizada = historietaRepo.save(historietaExistente);

        return historietaMapper.toGetDTO(historietaActualizada);
    };


    
    public void deleteHistorieta(Long id_historieta) {
        Historieta historieta_del = historietaRepo.findById(id_historieta)
                .orElseThrow(() -> new RuntimeException("Historieta no encontrada con el ID: " + id_historieta));

        historietaRepo.delete(historieta_del);
    };


}
