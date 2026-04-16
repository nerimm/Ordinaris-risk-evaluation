package com.ordenaris.riskEngine.rule.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.information.provider.VerificacionLegalProvider;

import lombok.extern.log4j.Log4j2;

@SpringBootTest(classes = EmpresaNuevaRule.class)
@Log4j2
public class EmpresaNuevaRuleTest {
	
	@Autowired
	private EmpresaNuevaRule rule;
	
	@MockitoBean
	private VerificacionLegalProvider provider;
	
	@Test
	public void given_Request_When_EmpresaNuevaRuleTest_Then_Success() {
		log.info("Start EmpresaNuevaRuleTest test: {}", rule);
		
		RequestDto request = new RequestDto();
		request.setEmpresaId("BBVA");
		
		NivelRiesgoEnum nivel;
		
		
		when(provider.getFechaCreacionEmpresa(request.getEmpresaId())).thenReturn(LocalDate.now().minusMonths(17));
		nivel = rule.apply(request, NivelRiesgoEnum.MEDIO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertNull(nivel);
		
		when(provider.getFechaCreacionEmpresa(request.getEmpresaId())).thenReturn(LocalDate.now().minusMonths(18));
		nivel = rule.apply(request, NivelRiesgoEnum.MEDIO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.MEDIO, nivel);
		
		when(provider.getFechaCreacionEmpresa(request.getEmpresaId())).thenReturn(LocalDate.now().minusMonths(19));
		nivel = rule.apply(request, NivelRiesgoEnum.MEDIO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.MEDIO, nivel);
	}

}
