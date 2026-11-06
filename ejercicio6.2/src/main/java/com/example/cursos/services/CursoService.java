package com.example.cursos.services;

import java.util.List;

import com.example.cursos.domain.Curso;
import com.example.cursos.domain.Tematica;

public interface CursoService {
    Curso añadir(Curso curso);
    List<Curso> obtenerTodos();
    Curso obtenerPorId(long id);
    Curso editar(Curso curso);
    void borrar(Long id);
    List<Curso> buscarPorNombre(String textoNombre);
    List<Curso> buscarPorTematica(Tematica tematica);
}
