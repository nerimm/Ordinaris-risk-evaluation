package com.ordenaris.riskEngine.rule.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.information.provider.VerificacionLegalProvider;
import com.ordenaris.riskEngine.rule.Rule;

@Component
@Order(4)
public class DemandaLegalAbiertaRule implements Rule {
	
	@Autowired
	private VerificacionLegalProvider provider;

	@Override
	public NivelRiesgoEnum apply(RequestDto request, NivelRiesgoEnum nivelRiesgoActual) {
		return (provider.existeJuicioMercantilEnCurso(request.getEmpresaId())) ? NivelRiesgoEnum.ALTO : null;
	}

}
