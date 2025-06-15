package com.laboratorio.SemanaDos.service;

import java.util.List;

import com.laboratorio.SemanaDos.model.Resultado;
import com.laboratorio.SemanaDos.model.Usuario;

public interface UsuarioServicio {
	public Resultado calcularInversion(Usuario form);

	public void guardarUsuario(Usuario usuario);

	public List<Usuario> listarTodosLosUsuarios();
}
