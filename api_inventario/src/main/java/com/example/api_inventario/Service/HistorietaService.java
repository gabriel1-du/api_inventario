package com.example.api_inventario.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.api_inventario.DTO.HistoriertaDTOs.getHistorietaDTO;
import com.example.api_inventario.DTO.HistoriertaDTOs.putHistorietaDTO;
import com.example.api_inventario.DTO.HistoriertaDTOs.saveHistorietaDTO;

@Service 
public interface HistorietaService {

    // Metodos Get
    public List<getHistorietaDTO> getAllHistorieta();

    public getHistorietaDTO getHistorietaById(Long id_historieta);
    // Fin Metodos Get

    //metodo ahora recibe dos elementos
    public getHistorietaDTO saveHistorieta(saveHistorietaDTO dto, MultipartFile imagen);

    public getHistorietaDTO putHistorieta(Long id_historieta, putHistorietaDTO historietaDto);

    public void deleteHistorieta(Long id_historieta);

}
