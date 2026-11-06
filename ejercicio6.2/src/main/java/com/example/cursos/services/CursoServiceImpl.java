package com.example.cursos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cursos.domain.Curso;
import com.example.cursos.domain.Tematica;

@Service
public class CursoServiceImpl implements CursoService {

    private static final double PRECIO_MAXIMO = 5000;

    private List<Curso> repositorio = new ArrayList<>();

    public Curso añadir(Curso curso) {
        if (curso.getId() == null)
            throw new RuntimeException("El id es obligatorio");
        for (Curso c : repositorio)
            if (c.getId().equals(curso.getId()))
                throw new RuntimeException("Ya existe un curso con id " + curso.getId());
        comprobarPrecio(curso);
        repositorio.add(curso);
        return curso;
    }

    public List<Curso> obtenerTodos() {
        return repositorio;
    }

    public Curso obtenerPorId(long id) {
        for (Curso curso : repositorio)
            if (curso.getId() == id)
                return curso;
        throw new RuntimeException("No existe el curso con id " + id);
    }

    public Curso editar(Curso curso) {
        comprobarPrecio(curso);
        int pos = repositorio.indexOf(obtenerPorId(curso.getId())); // lanza excepción si no existe
        repositorio.set(pos, curso);
        return curso;
    }

    public void borrar(Long id) {
        Curso curso = obtenerPorId(id); // lanza excepción si no existe
        repositorio.remove(curso);
    }

    public List<Curso> buscarPorNombre(String textoNombre) {
        textoNombre = textoNombre.toLowerCase();
        List<Curso> encontrados = new ArrayList<>();
        for (Curso curso : repositorio)
            if (curso.getNombre().toLowerCase().contains(textoNombre))
                encontrados.add(curso);
        return encontrados;
    }

    public List<Curso> buscarPorTematica(Tematica tematica) {
        List<Curso> encontrados = new ArrayList<>();
        for (Curso curso : repositorio)
            if (curso.getTematica() == tematica)
                encontrados.add(curso);
        return encontrados;
    }

    // Regla de negocio: va en la capa de servicio
    private void comprobarPrecio(Curso curso) {
        if (curso.getPrecio() == null || curso.getPrecio() < 0)
            throw new RuntimeException("El precio no es válido");
        if (curso.getPrecio() > PRECIO_MAXIMO)
            throw new RuntimeException("No se admiten cursos de más de 5000 euros");
    }
}
