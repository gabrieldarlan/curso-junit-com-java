package br.com.gdarlan.api.resources.exceptions;

import br.com.gdarlan.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {
    public static final String OBJETO_NAO_LOCALIZADO = "Objeto não localizado.";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema.";
    public static final int CODE_NOT_FOUND = 404;
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler.objectNotFound(new ObjectNotFoundException(OBJETO_NAO_LOCALIZADO),
                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJETO_NAO_LOCALIZADO, response.getBody().getError());
        assertEquals(CODE_NOT_FOUND, response.getBody().getStatus());

    }

    @Test
    void dataIntegrityViolation() {
    }
}