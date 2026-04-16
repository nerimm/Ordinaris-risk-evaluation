package com.ordenaris.riskEngine.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ordenaris.riskEngine.dto.catalog.ProductoFinancieroEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
	
	private String empresaId;
	
	private BigDecimal montoSolicitado;
	
	private ProductoFinancieroEnum productoFinanciero;
	
	private LocalDate fechaSolicitud;
	
}
