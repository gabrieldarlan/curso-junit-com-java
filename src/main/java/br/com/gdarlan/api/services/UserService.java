package br.com.gdarlan.api.services;

import br.com.gdarlan.api.domain.Users;

import java.util.List;

public interface UserService {
    Users findById(Integer id);

    List<Users> findAll();
}
