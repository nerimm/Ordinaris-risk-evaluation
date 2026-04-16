package com.ordenaris.riskEngine.rule.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.information.provider.DatosContablesProvider;
import com.ordenaris.riskEngine.rule.Rule;

@Component
@Order(2)
public class AltaSolicitudVersusVentasRule implements Rule {
	
	public static BigDecimal BIG_DEC_EIGHT = new BigDecimal(8);
	
	@Autowired
	private DatosContablesProvider provider;
	
	@Override
	public NivelRiesgoEnum apply(RequestDto request, NivelRiesgoEnum nivelRiesgoActual) {
		BigDecimal montoSolicitadoToCompare = provider.getPromedioMensualVentas().multiply(BIG_DEC_EIGHT);
		
		if (request.getMontoSolicitado().compareTo(montoSolicitadoToCompare) > 0) {
			return NivelRiesgoEnum.ALTO;
		} else {
			return null;
		}
	}

}
