package br.com.gdarlan.api.services;

import br.com.gdarlan.api.domain.User;

public interface UserService {
    User findById(Integer id);
}
