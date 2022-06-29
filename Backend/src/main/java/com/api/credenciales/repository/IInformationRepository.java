package com.api.credenciales.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.credenciales.model.Information;

@Repository
public interface IInformationRepository extends JpaRepository< Information , UUID > {
	
}
