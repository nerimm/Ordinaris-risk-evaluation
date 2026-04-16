package com.ordenaris.riskEngine.information.provider;

import java.time.LocalDate;

public interface VerificacionLegalProvider extends InformationProvider {
	
	LocalDate getFechaCreacionEmpresa(String empresaId);
	
	boolean existeJuicioMercantilEnCurso(String empresaId);
	
}
