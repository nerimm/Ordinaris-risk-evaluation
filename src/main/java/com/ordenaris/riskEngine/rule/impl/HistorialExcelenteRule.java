package com.ordenaris.riskEngine.rule.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.information.provider.HistorialPagosProvider;
import com.ordenaris.riskEngine.rule.Rule;

@Component
@Order(5)
public class HistorialExcelenteRule implements Rule {
	
	@Autowired
	private HistorialPagosProvider provider;
	
	@Override
	public NivelRiesgoEnum apply(RequestDto request, NivelRiesgoEnum nivelRiesgoActual) {
		if (provider.ultimosPagosEnTiempo(request.getEmpresaId())) {
			if (nivelRiesgoActual != null && nivelRiesgoActual != NivelRiesgoEnum.BAJO) {
				nivelRiesgoActual = NivelRiesgoEnum.values()[nivelRiesgoActual.ordinal() - 1];
			}
		}
		return nivelRiesgoActual;
	}

}
