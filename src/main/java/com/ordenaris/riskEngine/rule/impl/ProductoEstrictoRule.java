package com.ordenaris.riskEngine.rule.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.dto.catalog.ProductoFinancieroEnum;
import com.ordenaris.riskEngine.rule.Rule;

@Component
@Order(6)
public class ProductoEstrictoRule implements Rule {
	
	@Override
	public NivelRiesgoEnum apply(RequestDto request, NivelRiesgoEnum nivelRiesgoActual) {
		if (ProductoFinancieroEnum.ARRENDAMIENTO_FINANCIERO == request.getProductoFinanciero()) {
			if (nivelRiesgoActual != null && nivelRiesgoActual != NivelRiesgoEnum.RECHAZADO) {
				nivelRiesgoActual = NivelRiesgoEnum.values()[nivelRiesgoActual.ordinal() + 1];
			}
		}
		return nivelRiesgoActual;
	}

}
