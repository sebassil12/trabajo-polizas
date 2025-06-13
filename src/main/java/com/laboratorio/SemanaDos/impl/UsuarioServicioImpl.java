package com.laboratorio.SemanaDos.impl;

import org.springframework.stereotype.Service;

import com.laboratorio.SemanaDos.model.Resultado;
import com.laboratorio.SemanaDos.model.Usuario;
import com.laboratorio.SemanaDos.service.UsuarioServicio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	
	public Resultado calcularInversion(Usuario form) {
    Resultado result = new Resultado();

    double P = form.getCapitalInicial();
    int n = form.getNumCapitalizacionesAnio();
    int t = form.getTiempoAnios();
    double r; // Tasa de interés anual

    // Determinar la tasa de interés según la capitalización 
    if (n == 2) { // Semestral 
        r = 0.05; // 5% 
    } else if (n == 4) { // Trimestral 
        r = 0.07; // 7% 
    } else if (n == 12) { // Mensual 
        r = 0.11; // 11% 
    } else {
        // Manejar un caso por defecto o lanzar una excepción si n no es 2, 4 o 12
        throw new IllegalArgumentException("Número de capitalizaciones por año no soportado. Debe ser 2 (semestral), 4 (trimestral) o 12 (mensual).");
    }

    // Calcular el monto acumulado (valor futuro) usando la fórmula de interés compuesto 
    // A = P * (1 + r/n)^(n*t) 
    double A = P * Math.pow((1 + r / n), (n * t));
    result.setMontoAcumulado(A);

    // Calcular el interés ganado
    double interesGanado = A - P;
    result.setInteresGanado(interesGanado);

    // Categorizar al cliente 
    result.setCategoriaCliente(categorizarCliente(P, n));

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

}
