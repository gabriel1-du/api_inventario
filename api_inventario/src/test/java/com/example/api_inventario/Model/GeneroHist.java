package com.example.api_inventario.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
