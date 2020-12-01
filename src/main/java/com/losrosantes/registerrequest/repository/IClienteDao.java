package com.losrosantes.registerrequest.repository;

import com.losrosantes.registerrequest.domain.RegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteDao extends JpaRepository<RegisterRequest, Long> {}
