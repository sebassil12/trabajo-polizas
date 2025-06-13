package com.laboratorio.SemanaDos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name="usuario")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nombres;
	private String cedula;
	private String email;
	private String celular;
	
    private Double capitalInicial; // P
    
    private Integer numCapitalizacionesAnio; // n
    
    private Integer tiempoAnios; // t
}
