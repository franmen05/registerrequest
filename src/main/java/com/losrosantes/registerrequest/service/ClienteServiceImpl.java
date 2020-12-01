package com.losrosantes.registerrequest.service;

import com.losrosantes.registerrequest.domain.RegisterRequest;
import com.losrosantes.registerrequest.repository.IClienteDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<RegisterRequest> findAll() {
        return (List<RegisterRequest>) clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RegisterRequest> findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterRequest findById(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public RegisterRequest save(RegisterRequest cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDao.deleteById(id);
    }
}
