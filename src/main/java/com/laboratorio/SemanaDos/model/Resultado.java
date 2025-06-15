package com.laboratorio.SemanaDos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name="result")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Resultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double montoAcumulado;
    private double interesGanado;
    private String categoriaCliente;

    // Relación con Usuario (uno a uno o uno a muchos)
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
