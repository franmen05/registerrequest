package com.losrosantes.registerrequest.service;

import com.losrosantes.registerrequest.domain.RegisterRequest;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService {
    List<RegisterRequest> findAll();

    Page<RegisterRequest> findAll(Pageable pageable);

    RegisterRequest findById(Long id);

    RegisterRequest save(RegisterRequest cliente);

    void delete(Long id);
}
