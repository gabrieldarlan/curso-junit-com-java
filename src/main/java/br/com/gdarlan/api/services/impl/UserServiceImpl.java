package br.com.gdarlan.api.services.impl;

import br.com.gdarlan.api.domain.Users;
import br.com.gdarlan.api.repositories.UserRepository;
import br.com.gdarlan.api.services.UserService;
import br.com.gdarlan.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Objeto não localizado."));
    }

    @Override
    public List<Users> findAll() {
        return repository.findAll();
    }
}
