package com.ordenaris.riskEngine.rule.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.information.provider.HistorialPagosProvider;

import lombok.extern.log4j.Log4j2;

@SpringBootTest(classes = HistorialExcelenteRule.class)
@Log4j2
public class HistorialExcelenteRuleTest {
	
	@Autowired
	private HistorialExcelenteRule rule;
	
	@MockitoBean
	private HistorialPagosProvider provider;
	
	@Test
	public void given_Request_When_HistorialExcelenteRuleTest_Then_Success() {
		log.info("Start HistorialExcelenteRuleTest test: {}", rule);
		
		RequestDto request = new RequestDto();
		request.setEmpresaId("BBVA");
		
		NivelRiesgoEnum nivel;
		
		when(provider.ultimosPagosEnTiempo(request.getEmpresaId())).thenReturn(true);
		nivel = rule.apply(request, NivelRiesgoEnum.BAJO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.BAJO, nivel);
		
		when(provider.ultimosPagosEnTiempo(request.getEmpresaId())).thenReturn(true);
		nivel = rule.apply(request, NivelRiesgoEnum.MEDIO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.BAJO, nivel);
		
		when(provider.ultimosPagosEnTiempo(request.getEmpresaId())).thenReturn(true);
		nivel = rule.apply(request, NivelRiesgoEnum.ALTO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.MEDIO, nivel);
		
		when(provider.ultimosPagosEnTiempo(request.getEmpresaId())).thenReturn(true);
		nivel = rule.apply(request, NivelRiesgoEnum.RECHAZADO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.ALTO, nivel);
		
		

		when(provider.ultimosPagosEnTiempo(request.getEmpresaId())).thenReturn(false);
		nivel = rule.apply(request, NivelRiesgoEnum.BAJO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.BAJO, nivel);
		
		when(provider.ultimosPagosEnTiempo(request.getEmpresaId())).thenReturn(false);
		nivel = rule.apply(request, NivelRiesgoEnum.MEDIO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.MEDIO, nivel);
		
		when(provider.ultimosPagosEnTiempo(request.getEmpresaId())).thenReturn(false);
		nivel = rule.apply(request, NivelRiesgoEnum.ALTO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.ALTO, nivel);
		
		when(provider.ultimosPagosEnTiempo(request.getEmpresaId())).thenReturn(false);
		nivel = rule.apply(request, NivelRiesgoEnum.RECHAZADO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.RECHAZADO, nivel);
	}
	
}

