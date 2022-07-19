package com.api.credenciales.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;

@Repository
public interface IIdentityRepository extends JpaRepository< Identity , UUID > {
  
  Optional< Identity > findByInformation( Information information ) ;
  
}
