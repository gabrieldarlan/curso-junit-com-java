package br.com.gdarlan.api.config;

import br.com.gdarlan.api.domain.Users;
import br.com.gdarlan.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){
        Users u1 = new Users(null, "maria", "maria@maria.com", "1234");
        Users u2 = new Users(null, "joao", "joao@joao.com", "1234");

        repository.saveAll(List.of(u1,u2));

    }
}
