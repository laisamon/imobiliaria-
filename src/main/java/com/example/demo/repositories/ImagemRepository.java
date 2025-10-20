package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ImagemModel;

public interface ImagemRepository extends JpaRepository<ImagemModel, Integer> {
    
}
