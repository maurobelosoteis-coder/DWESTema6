package com.example.cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cursos.domain.Curso;
import com.example.cursos.domain.Tematica;
import com.example.cursos.services.CursoService;

@Controller
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // ---------- LISTADO ----------
    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaCursos", cursoService.obtenerTodos());
        model.addAttribute("findForm", new Curso()); // para búsq. por nombre
        return "listView";
    }

    // ---------- VER UNO ----------
    @GetMapping("/ver/{id}")
    public String showElement(@PathVariable long id, Model model) {
        try {
            model.addAttribute("curso", cursoService.obtenerPorId(id));
            return "listOneView";
        } catch (RuntimeException e) {
            return mostrarListaConError(model, e.getMessage());
        }
    }

    // ---------- ALTA ----------
    @GetMapping("/nuevo")
    public String showNew(Model model) {
        model.addAttribute("cursoForm", new Curso());
        return "newFormView";
    }

    @PostMapping("/nuevo/submit")
    public String showNewSubmit(Curso cursoForm, Model model) {
        try {
            cursoService.añadir(cursoForm);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("cursoForm", cursoForm); // no perdemos lo escrito
            model.addAttribute("msgError", e.getMessage());
            return "newFormView";
        }
    }

    // ---------- MODIFICACIÓN ----------
    @GetMapping("/editar/{id}")
    public String showEdit(@PathVariable long id, Model model) {
        try {
            model.addAttribute("cursoForm", cursoService.obtenerPorId(id));
            return "editFormView";
        } catch (RuntimeException e) {
            return mostrarListaConError(model, e.getMessage());
        }
    }

    @PostMapping("/editar/submit")
    public String showEditSubmit(Curso cursoForm, Model model) {
        try {
            cursoService.editar(cursoForm);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("cursoForm", cursoForm);
            model.addAttribute("msgError", e.getMessage());
            return "editFormView";
        }
    }

    // ---------- BAJA ----------
    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id, Model model) {
        try {
            cursoService.borrar(id);
            return "redirect:/";
        } catch (RuntimeException e) {
            return mostrarListaConError(model, e.getMessage());
        }
    }

    // ---------- FILTRO POR NOMBRE ----------
    @PostMapping("/findByName")
    public String showFindByNameSubmit(Curso cursoForm, Model model) {
        model.addAttribute("listaCursos", cursoService.buscarPorNombre(cursoForm.getNombre()));
        model.addAttribute("findForm", cursoForm);
        return "listView";
    }

    // ---------- FILTRO POR TEMÁTICA ----------
    @GetMapping("/findByTematica/{tematica}")
    public String showFindByTematica(@PathVariable Tematica tematica, Model model) {
        model.addAttribute("listaCursos", cursoService.buscarPorTematica(tematica));
        model.addAttribute("findForm", new Curso()); // para búsq. por nombre
        model.addAttribute("tematicaSeleccionada", tematica);
        return "listView";
    }

    // Vuelve al listado completo mostrando un mensaje de error
    private String mostrarListaConError(Model model, String mensaje) {
        model.addAttribute("listaCursos", cursoService.obtenerTodos());
        model.addAttribute("findForm", new Curso());
        model.addAttribute("msgError", mensaje);
        return "listView";
    }
}
