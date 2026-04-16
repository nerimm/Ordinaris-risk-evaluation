package com.ordenaris.riskEngine.rule.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.information.provider.VerificacionLegalProvider;
import com.ordenaris.riskEngine.rule.Rule;

@Component
@Order(3)
public class EmpresaNuevaRule implements Rule {
	
	@Autowired
	private VerificacionLegalProvider provider;
	
	@Override
	public NivelRiesgoEnum apply(RequestDto request, NivelRiesgoEnum nivelRiesgoActual) {
		if (provider.getFechaCreacionEmpresa(request.getEmpresaId())
				.isAfter(LocalDate.now().minusMonths(18))) {
			if (NivelRiesgoEnum.MEDIO == nivelRiesgoActual) {
				nivelRiesgoActual = null;
			}
		}
		return nivelRiesgoActual;
	}
}
