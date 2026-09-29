package com.example.api_inventario.Model;

import org.hibernate.annotations.Audited.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
