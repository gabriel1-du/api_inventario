package com.example.api_inventario.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "HISTORIETA")
@Data
@NoArgsConstructor
public class Historieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_historieta;

    @Column(name = "nombre_historieta", nullable = false, length = 30)
    private String nombre_historieta;

    @Column(name = "descripcion_historieta", nullable = false, columnDefinition = "TEXT")
    private String descripcion_historieta;

    @Column(name = "portada", nullable = false, columnDefinition = "TEXT")
    private String portada;

    @Column(name = "numero_historieta", nullable = false)
    private Integer numero_historieta;

    @Column(name = "precio", nullable = false, precision = 8, scale = 2)
    private BigDecimal precio;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero", nullable = false)
    private GeneroHist genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_hist", nullable = false)
    private TipoHist tipoHist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    private AutorHist autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_serie_hist", nullable = false)
    private SerieHist serieHist;
}