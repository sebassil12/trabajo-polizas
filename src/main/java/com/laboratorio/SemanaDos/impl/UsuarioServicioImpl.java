package com.laboratorio.SemanaDos.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratorio.SemanaDos.model.Resultado;
import com.laboratorio.SemanaDos.model.Usuario;
import com.laboratorio.SemanaDos.repository.ResultadoRepositorio;
import com.laboratorio.SemanaDos.repository.UsuarioRepositorio;
import com.laboratorio.SemanaDos.service.UsuarioServicio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ResultadoRepositorio resultadoRepositorio;

    public Resultado calcularInversion(Usuario form) {
        // Validar que todos los campos requeridos no sean nulos
        if (form.getCapitalInicial() == null || form.getNumCapitalizacionesAnio() == null || form.getTiempoAnios() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        double P = form.getCapitalInicial();
        int n = form.getNumCapitalizacionesAnio();
        int t = form.getTiempoAnios();

        // Definir tasas como constantes
        final double TASA_SEMESTRAL = 0.05; // 5%
        final double TASA_TRIMESTRAL = 0.07; // 7%
        final double TASA_MENSUAL = 0.11; // 11%

        double r;

        switch (n) {
            case 2:
                r = TASA_SEMESTRAL;
                break;
            case 4:
                r = TASA_TRIMESTRAL;
                break;
            case 12:
                r = TASA_MENSUAL;
                break;
            default:
                throw new IllegalArgumentException("Número de capitalizaciones por año no soportado. Debe ser 2 (semestral), 4 (trimestral) o 12 (mensual).");
        }

        // Calcular monto acumulado con interés compuesto
        double A = P * Math.pow(1 + (r / n), n * t);
        double interesGanado = A - P;

        // Redondear a dos decimales para mostrar resultados limpios
        A = redondear(A);
        interesGanado = redondear(interesGanado);

        // Crear resultado
        Resultado result = new Resultado();
        result.setMontoAcumulado(A);
        result.setInteresGanado(interesGanado);
        result.setCategoriaCliente(categorizarCliente(P, n));
        result.setUsuario(form);

        resultadoRepositorio.save(result);

        return result;
    }

    private String categorizarCliente(Double capitalInicial, Integer numCapitalizacionesAnio) {
        if (numCapitalizacionesAnio == 12) { // Solo se categoriza si la capitalización es mensual 
            if (capitalInicial >= 100 && capitalInicial <= 500) { // Capital inicial entre 100 y 500 USD 
                return "Categoría 3";
            } else if (capitalInicial > 500 && capitalInicial <= 1000) { // Capital inicial entre 501 y 1000 USD 
                return "Categoría 2";
            } else if (capitalInicial > 1000) { // Capital inicial superior a 1000 USD 
                return "Categoría 1";
            }
        }
        return "Sin Categoría (No aplica o n no es 12)"; // Para otros casos o cuando n no es 12
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    @Override
    public List<Usuario> listarTodosLosUsuarios() {
    return usuarioRepositorio.findAll();
    }

    private double redondear(double valor) {
    return Math.round(valor * 100.0) / 100.0;
    }

}
