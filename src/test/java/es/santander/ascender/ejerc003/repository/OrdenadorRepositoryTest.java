package es.santander.ascender.ejerc003.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc003.model.Ordenador;

@SpringBootTest
public class OrdenadorRepositoryTest {

    @Autowired
    private OrdenadorRepository ordenadorRepository;

    private Ordenador ordenador;

    
    @BeforeEach
    public void setUp() {
        
        ordenador = new Ordenador(null, 255, 255, 255, 100, true, 23.65f);
    }

    // Test: Listar todos los ordenadores
    @Test
    public void testList() {
        
        ordenadorRepository.save(ordenador);

        // Listamos todos los ordenadores
        Iterable<Ordenador> valores = ordenadorRepository.findAll();

        // Comprobar que valore no es nulo y que el ordenador se guardó en la lista
        assertNotNull(valores);
    }

    @Test
    public void testNoExiste() {
        // ID que no exista
        Optional<Ordenador> resultado = ordenadorRepository.findById(45L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testLeerUno() {
        
        Ordenador ordenadorAGuardar = new Ordenador(1L, 255, 255, 255, 100, true, 23.65f);
        ordenadorRepository.save(ordenadorAGuardar);

        Optional<Ordenador> resultado = ordenadorRepository.findById(ordenadorAGuardar.getId());

        // Verificamos que el ordenador existe
        assertFalse(resultado.isEmpty());
        assertEquals(ordenadorAGuardar.getId(), resultado.get().getId());
    }

}
