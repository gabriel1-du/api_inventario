package com.example.api_inventario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_inventario.Model.SerieHist;

@Repository 
public interface SerieHistRepository extends JpaRepository<SerieHist, Long>{

}
