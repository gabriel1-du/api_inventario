package com.example.api_inventario.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TIPO_HIST")
@Data
@NoArgsConstructor
public class TipoHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_hist;

    @Column(name = "nombre_tipo", nullable = false, length = 10)
    private String nombre_tipo;
}