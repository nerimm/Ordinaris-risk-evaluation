package com.ordenaris.riskEngine.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.rule.Rule;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class OrdenarisRiskEngine {
	
	private final List<Rule> rules;
	
	@Autowired
	public OrdenarisRiskEngine(List<Rule> rules) {
		this.rules = rules;
	}
		
	public NivelRiesgoEnum evaluateRules(RequestDto request) throws Exception {
		log.info("Apply rules...");
		log.info("Request: {}", request);
		
		NivelRiesgoEnum nivelRiesgoActual = null;
		for(Rule rule : rules) {
			log.info("Apply rule: {}", rule.getClass().getSimpleName());
			nivelRiesgoActual = rule.apply(request, nivelRiesgoActual);
			log.info("\trule result: {}", nivelRiesgoActual);
			if (nivelRiesgoActual != null) {
				log.info("Resultado NIVEL DE RIESGO: {}", nivelRiesgoActual);
				break;
			}
		}
		
		if (nivelRiesgoActual == null) {
			log.warn("No se pudo determinar el nivel de riesgo para la empresa");
		}
		
		log.info("Finish");

		return nivelRiesgoActual;
	}
	
}

