package com.ordenaris.riskEngine.rule.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.information.provider.VerificacionLegalProvider;

import lombok.extern.log4j.Log4j2;

@SpringBootTest(classes = DemandaLegalAbiertaRule.class)
@Log4j2
public class DemandaLegalAbiertaRuleTest {
	
	@Autowired
	private DemandaLegalAbiertaRule rule;

	@MockitoBean
	private VerificacionLegalProvider provider;

	@Test
	public void given_Request_When_DemandaLegalAbiertaRuleTest_Then_Success() {
		log.info("Start DemandaLegalAbiertaRuleTest test: {}", rule);
		
		RequestDto request = new RequestDto();
		request.setEmpresaId("BBVA");
		
		NivelRiesgoEnum nivel;
		
		when(provider.existeJuicioMercantilEnCurso(request.getEmpresaId())).thenReturn(false);
		nivel = rule.apply(request, null);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertNull(nivel);
		
		when(provider.existeJuicioMercantilEnCurso(request.getEmpresaId())).thenReturn(true);
		nivel = rule.apply(request, null);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.ALTO, nivel);
		
	}
	
}
