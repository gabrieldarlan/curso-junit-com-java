package br.com.gdarlan.api.services;

import br.com.gdarlan.api.domain.Users;
import br.com.gdarlan.api.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    Users findById(Integer id);

    List<Users> findAll();

    Users create(UserDto obj);
}
