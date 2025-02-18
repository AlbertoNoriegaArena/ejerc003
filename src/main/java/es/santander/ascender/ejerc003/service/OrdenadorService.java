package es.santander.ascender.ejerc003.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santander.ascender.ejerc003.model.Ordenador;
import es.santander.ascender.ejerc003.repository.OrdenadorRepository;
import jakarta.annotation.PostConstruct;

@Service
public class OrdenadorService {

    @Autowired
    private OrdenadorRepository repository;

    // Crear registros
    @PostConstruct
    public void init() {
        // Solo crear si no existen registros para evitar duplicados
        if (repository.count() == 0) {
            Ordenador ordenador1 = new Ordenador(null, 255, 0, 120, 100, false, 20.15f);
            Ordenador ordenador2 = new Ordenador(null, 0, 0, 255, 20, true, 1);
            repository.save(ordenador1);
            repository.save(ordenador2);
        }
    }

    public Ordenador create(Ordenador ordenador) {

        // repository.save() normalmente devuelve la misma instancia pero no está
        // garantizado.
        // Como no está garantizado tienes que devolver el objeto

        if (ordenador.getId() != null) {
            throw new CRUDSecurityException("Han tratado de modificar un registro columna",
                    CRUDOperation.CREATE,
                    ordenador.getId());
        } else {
            return repository.save(ordenador);
        }

    }

    // Leer uno
    public Ordenador read(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Listar
    public List<Ordenador> list() {
        return repository.findAll();
    }

    // Con el update ya tienes el id. En teoria podrías devolver vacio aunque lo
    // puedes dejar asi también.
    public Ordenador update(Ordenador ordenador) {
        if (ordenador.getId() == null) {
            throw new CRUDSecurityException("Han tratado de crear un registro columna utilizando modificar",
                    CRUDOperation.UPDATE,
                    null);
        }
        return repository.save(ordenador);
    }

    // DELETE
    // Si no puede borrar salta con excepción
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
