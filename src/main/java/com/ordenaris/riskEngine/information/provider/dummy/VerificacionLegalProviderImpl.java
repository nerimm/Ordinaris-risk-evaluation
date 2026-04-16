package com.ordenaris.riskEngine.information.provider.dummy;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.ordenaris.riskEngine.information.provider.VerificacionLegalProvider;

@Component
public class VerificacionLegalProviderImpl implements VerificacionLegalProvider {

	@Override
	public LocalDate getFechaCreacionEmpresa(String empresaId) {
		return LocalDate.of(2025, 10, 18);
	}

	@Override
	public boolean existeJuicioMercantilEnCurso(String empresaId) {
		return true;
	}
	
}
