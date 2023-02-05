package br.com.gdarlan.api.services.impl;

import br.com.gdarlan.api.domain.Users;
import br.com.gdarlan.api.domain.dto.UserDto;
import br.com.gdarlan.api.repositories.UserRepository;
import br.com.gdarlan.api.services.exceptions.DataIntegrityViolationException;
import br.com.gdarlan.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "maria";
    public static final String EMAIL = "maria@maria.com";
    public static final String PASSWORD = "1234";
    public static final String OBJETO_NAO_LOCALIZADO = "Objeto não localizado.";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    private Users users;
    private UserDto userDto;
    private Optional<Users> optionalUsers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUsers);
        Users response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException() {
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_LOCALIZADO));
        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_LOCALIZADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(users));

        List<Users> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Users.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(users);

        Users response = service.create(userDto);

        assertNotNull(response);

        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());


    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUsers);
        try {
            optionalUsers.get().setId(2);
            service.create(userDto);
        } catch (Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("E-mail já cadastrado no sistema.", e.getMessage());
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        users = new Users(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NAME, EMAIL, PASSWORD));
    }
}