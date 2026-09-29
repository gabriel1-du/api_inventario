package com.example.api_inventario.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "SERIE_HIST")
@Data
@NoArgsConstructor
public class SerieHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_serie_hist;

    @Column(name = "nombre_serie_hist", nullable = false, length = 30)
    private String nombre_serie_hist;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero", nullable = false)
    private GeneroHist genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    private AutorHist autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_hist", nullable = false)
    private TipoHist tipoHist;
}