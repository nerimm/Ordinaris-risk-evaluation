package com.ordenaris.riskEngine.information.provider;

public interface HistorialPagosProvider extends InformationProvider {
	
	int getDiasDeudaVencida(String empresaId);
	
	/**
	 * Indicador de que los ultimos 12 pagos fueron en tiempo y sin refinanciamiento
	 * @param empresaId
	 * @return
	 */
	boolean ultimosPagosEnTiempo(String empresaId);
	
}
