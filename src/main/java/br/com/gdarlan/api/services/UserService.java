package br.com.gdarlan.api.services;

import br.com.gdarlan.api.domain.Users;

public interface UserService {
    Users findById(Integer id);
}
