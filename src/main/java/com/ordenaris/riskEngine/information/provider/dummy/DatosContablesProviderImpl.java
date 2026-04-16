package com.ordenaris.riskEngine.information.provider.dummy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ordenaris.riskEngine.information.provider.DatosContablesProvider;

@Component
public class DatosContablesProviderImpl implements DatosContablesProvider {

	@Override
	public BigDecimal getPromedioMensualVentas() {
		return new BigDecimal(10);
	}
	
	
}
