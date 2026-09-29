package com.example.api_inventario.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "GENERO_HIST")
@Data
@NoArgsConstructor
public class GeneroHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_genero;

    @Column(name = "nombre_genero", nullable = false, length = 20)
    private String nombre_genero;
}