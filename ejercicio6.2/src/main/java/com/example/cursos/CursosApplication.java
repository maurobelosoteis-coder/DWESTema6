package com.example.cursos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cursos.domain.Curso;
import com.example.cursos.domain.Tematica;
import com.example.cursos.services.CursoService;

@SpringBootApplication
public class CursosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CursosApplication.class, args);
    }

    // Datos de ejemplo al arrancar
    @Bean
    CommandLineRunner initData(CursoService cursoService) {
        return args -> {
            cursoService.añadir(new Curso(1L, "Curso de Programación desde cero", 1000.0, Tematica.PROGRAMACION));
            cursoService.añadir(new Curso(2L, "Aprende Java Profesional", 1500.0, Tematica.PROGRAMACION));
            cursoService.añadir(new Curso(3L, "Redes locales a tu alcance", 800.0, Tematica.REDES));
            cursoService.añadir(new Curso(4L, "Seguridad en Internet", 1800.0, Tematica.REDES));
            cursoService.añadir(new Curso(5L, "Sistemas operativos avanzados", 2800.0, Tematica.SISTEMAS));
            cursoService.añadir(new Curso(6L, "Inteligencia artificial para dummies", 300.0, Tematica.OTROS));
        };
    }
}
