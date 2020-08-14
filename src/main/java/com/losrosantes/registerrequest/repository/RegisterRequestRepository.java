package com.losrosantes.registerrequest.repository;

import com.losrosantes.registerrequest.domain.RegisterRequest;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RegisterRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegisterRequestRepository extends JpaRepository<RegisterRequest, Long> {
}
