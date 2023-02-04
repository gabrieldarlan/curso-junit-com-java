package br.com.gdarlan.api.services.impl;

import br.com.gdarlan.api.domain.Users;
import br.com.gdarlan.api.repositories.UserRepository;
import br.com.gdarlan.api.services.UserService;
import br.com.gdarlan.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Objeto não localizado."));
    }
}
