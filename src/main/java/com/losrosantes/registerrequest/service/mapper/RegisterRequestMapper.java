package com.losrosantes.registerrequest.service.mapper;


import com.losrosantes.registerrequest.domain.*;
import com.losrosantes.registerrequest.service.dto.RegisterRequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RegisterRequest} and its DTO {@link RegisterRequestDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RegisterRequestMapper extends EntityMapper<RegisterRequestDTO, RegisterRequest> {



    default RegisterRequest fromId(Long id) {
        if (id == null) {
            return null;
        }
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setId(id);
        return registerRequest;
    }
}
