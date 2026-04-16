package com.ordenaris.riskEngine.information.provider;

import java.math.BigDecimal;

public interface DatosContablesProvider extends InformationProvider {
	
	BigDecimal getPromedioMensualVentas();
	
}
