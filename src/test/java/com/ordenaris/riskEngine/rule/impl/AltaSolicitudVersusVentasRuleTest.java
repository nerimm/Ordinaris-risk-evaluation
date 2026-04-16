package com.ordenaris.riskEngine.rule.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.information.provider.DatosContablesProvider;

import lombok.extern.log4j.Log4j2;

@SpringBootTest(classes = AltaSolicitudVersusVentasRule.class)
@Log4j2
public class AltaSolicitudVersusVentasRuleTest {

	@Autowired
	private AltaSolicitudVersusVentasRule rule;

	@MockitoBean
	private DatosContablesProvider provider;

	@Test
	public void given_Request_When_AltaSolicitudVersusVentasRuleApply_Then_Success() {
		log.info("Start AltaSolicitudVersusVentasRule test: {}", rule);
		
		when(provider.getPromedioMensualVentas()).thenReturn(new BigDecimal(100));

		RequestDto request = new RequestDto();

		NivelRiesgoEnum nivel;

		request.setMontoSolicitado(new BigDecimal(50));
		nivel = rule.apply(request, null);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertNull(nivel);
		
		request.setMontoSolicitado(new BigDecimal(100));
		nivel = rule.apply(request, null);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertNull(nivel);
		
		request.setMontoSolicitado(new BigDecimal(800));
		nivel = rule.apply(request, null);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertNull(nivel);
		
		request.setMontoSolicitado(new BigDecimal(801));
		nivel = rule.apply(request, null);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.ALTO, nivel);

	}

}
