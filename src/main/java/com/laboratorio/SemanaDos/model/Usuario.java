package com.laboratorio.SemanaDos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	@NotBlank(message = "El campo nombres es obligatorio")
	private String nombres;

	@NotBlank(message = "El campo cedula es obligatorio")
	private String cedula;
	
	@Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
	private String email;

	@NotBlank(message = "El celular es obligatorio")
	private String celular;
	
	@NotNull(message = "El capital inicial es obligatorio")
    @DecimalMin(value = "100.00", message = "El capital debe ser mayor que 100.00")
    private Double capitalInicial; // P
    
	@NotNull(message = "Las capitalizaciones anuales son obligatorias")
    // @Min(value = 1, message = "Debe haber al menos una capitalización por año")
    private Integer numCapitalizacionesAnio; // n
    
	@NotNull(message = "El tiempo en años es obligatorio")
    @Min(value = 1, message = "El tiempo debe ser al menos un año")
    private Integer tiempoAnios; // t

	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private Resultado resultado;
}
