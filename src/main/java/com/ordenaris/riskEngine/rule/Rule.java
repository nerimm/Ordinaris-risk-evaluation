package com.ordenaris.riskEngine.rule;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;

public interface Rule {
	
	public NivelRiesgoEnum apply(RequestDto request, NivelRiesgoEnum nivelRiesgoActual);
	
}
