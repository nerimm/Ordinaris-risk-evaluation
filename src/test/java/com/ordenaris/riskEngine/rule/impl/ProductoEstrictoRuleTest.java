package com.ordenaris.riskEngine.rule.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.dto.catalog.ProductoFinancieroEnum;

import lombok.extern.log4j.Log4j2;

@SpringBootTest(classes = ProductoEstrictoRule.class)
@Log4j2
public class ProductoEstrictoRuleTest {
	
	@Autowired
	private ProductoEstrictoRule rule;
	
	@Test
	public void given_Request_When_ProductoEstrictoRuleTest_Then_Success() {
		log.info("Start ProductoEstrictoRuleTest test: {}", rule);
		
		RequestDto request = new RequestDto();
		request.setEmpresaId("BBVA");
		request.setProductoFinanciero(ProductoFinancieroEnum.ARRENDAMIENTO_FINANCIERO);
		
		NivelRiesgoEnum nivel;
		
		nivel = rule.apply(request, NivelRiesgoEnum.BAJO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.MEDIO, nivel);
		
		nivel = rule.apply(request, NivelRiesgoEnum.MEDIO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.ALTO, nivel);
		
		nivel = rule.apply(request, NivelRiesgoEnum.ALTO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.RECHAZADO, nivel);
		
		nivel = rule.apply(request, NivelRiesgoEnum.RECHAZADO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.RECHAZADO, nivel);
		
		
		request.setProductoFinanciero(ProductoFinancieroEnum.CREDITO_REVOLVENTE);
		
		nivel = rule.apply(request, NivelRiesgoEnum.BAJO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.BAJO, nivel);
		
		nivel = rule.apply(request, NivelRiesgoEnum.MEDIO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.MEDIO, nivel);
		
		nivel = rule.apply(request, NivelRiesgoEnum.ALTO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.ALTO, nivel);
		
		nivel = rule.apply(request, NivelRiesgoEnum.RECHAZADO);
		log.info("Nivel de riesgo evaluado: {}", nivel);
		assertEquals(NivelRiesgoEnum.RECHAZADO, nivel);
	}

}
