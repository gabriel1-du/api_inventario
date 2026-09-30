package com.example.api_inventario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_inventario.Model.GeneroHist;

@Repository 
public interface GeneroHistRepository extends JpaRepository<GeneroHist, Long> {

}
