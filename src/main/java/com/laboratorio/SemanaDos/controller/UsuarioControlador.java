package com.laboratorio.SemanaDos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.laboratorio.SemanaDos.impl.UsuarioServicioImpl;
import com.laboratorio.SemanaDos.model.Resultado;
import com.laboratorio.SemanaDos.model.Usuario;

import jakarta.validation.Valid;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @GetMapping("/investment")
    public String showForm(Model model) {
        model.addAttribute("investmentForm", new Usuario());
        return "investmentForm"; // Nombre de tu plantilla HTML (ej. investmentForm.html)
    }

    @PostMapping("/calculate")
    public String calculateInvestment(@Valid @ModelAttribute("investmentForm") Usuario investmentForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("investmentForm", investmentForm);
            return "investmentForm"; // Vuelve al formulario si hay errores de validación
        }

        try {
            usuarioServicio.guardarUsuario(investmentForm); // Guardar el usuario
            Resultado result = usuarioServicio.calcularInversion(investmentForm);
            model.addAttribute("investmentResult", result);
            model.addAttribute("investmentForm", investmentForm); // Mantener los datos del formulario
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "investmentForm";
        }
        return "investmentForm"; // O redirige a una página de resultados si lo prefieres
    }

    @GetMapping("/usuarios")
    public String listUsuarios(Model model) {
        List<Usuario> usuarios = usuarioServicio.listarTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarioLista"; // Nombre del nuevo template
    }

    @GetMapping("/")
    public String showUsers(Model model) {
        List<Usuario> usuarios = usuarioServicio.listarTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarioLista";  // Nombre de tu plantilla HTML (ej. investmentForm.html)
    }
}