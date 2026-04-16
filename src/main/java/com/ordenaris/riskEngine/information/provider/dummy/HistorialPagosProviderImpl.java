package com.ordenaris.riskEngine.information.provider.dummy;

import org.springframework.stereotype.Component;

import com.ordenaris.riskEngine.information.provider.HistorialPagosProvider;

@Component
public class HistorialPagosProviderImpl implements HistorialPagosProvider {

	@Override
	public int getDiasDeudaVencida(String empresaId) {
		return 50;
	}

	@Override
	public boolean ultimosPagosEnTiempo(String empresaId) {
		return false;
	}
	
}
