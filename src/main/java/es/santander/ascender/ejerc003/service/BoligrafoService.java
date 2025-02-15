package es.santander.ascender.ejerc003.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santander.ascender.ejerc003.model.Boligrafo;
import es.santander.ascender.ejerc003.repository.BoligrafoRepository;
import jakarta.annotation.PostConstruct;

@Service
public class BoligrafoService {

    @Autowired
    private BoligrafoRepository repository;


    //Crear registros
    @PostConstruct
    public void init() {
        // Solo crear si no existen registros para evitar duplicados
        if (repository.count() == 0) {
            Boligrafo boligrafo1 = new Boligrafo(null, "#FF5733", true, "Bolígrafo Rojo");
            Boligrafo boligrafo2 = new Boligrafo(null, "#33FF57", false, "Bolígrafo Verde");
            repository.save(boligrafo1);
            repository.save(boligrafo2);
        }
    }

    public Boligrafo create(Boligrafo boligrafo) {

        // repository.save() normalmente devuelve la misma instancia pero no está
        // garantizado.
        // Como no está garantizado tienes que devolver el objeto

        if (boligrafo.getId() != null) {
            throw new CRUDSecurityException("Han tratado de modificar un registro columna",
                    CRUDOperation.CREATE,
                    boligrafo.getId());
        } else {
            return repository.save(boligrafo);
        }

    }

    // Leer uno
    public Boligrafo read(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Listar
    public List<Boligrafo> list() {
        return repository.findAll();
    }

    // Con el update ya tienes el id. En teoria podrías devolver vacio aunque lo
    // puedes dejar asi también.
    public Boligrafo update(Boligrafo boligrafo) {
        if (boligrafo.getId() == null) {
            throw new CRUDSecurityException("Han tratado de crear un registro columna utilizando modificar",
                    CRUDOperation.UPDATE,
                    null);
        }
        return repository.save(boligrafo);
    }

    // DELETE
    // Si no puede borrar salta con excepción
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
