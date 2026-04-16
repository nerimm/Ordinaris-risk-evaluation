package com.ordenaris.riskEngine;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.ProductoFinancieroEnum;
import com.ordenaris.riskEngine.engine.OrdenarisRiskEngine;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class RiskEngineStartupRunner implements CommandLineRunner {
	
	@Autowired
	private OrdenarisRiskEngine engine;
	
	@Override
	public void run(String... args) throws Exception {
		validateArguments(args);
		RequestDto request = createRequestDto(args);
		engine.evaluateRules(request);
	}
	
	private static void validateArguments(String[] args) throws Exception {
		if (args.length < 4) {
			throw new Exception("Se deben mandar 4 argumentos");
		} else {
			log.info("Argument[0]: {}", args[0]);	// Id de la empresa <cadena>
			log.info("Argument[1]: {}", args[1]);	// Monto solicitado <decimal>
			log.info("Argument[2]: {}", args[2]);	// Producto financiero  <LINEA_OPERATIVA, CREDITO_REVOLVENTE, ARRENDAMIENTO_FINANCIERO>
			log.info("Argument[3]: {}", args[3]);	// fecha solicitud <aaaa-MM-dd>
		}
	}
	
	private RequestDto createRequestDto(String[] arguments) {
		RequestDto request = new RequestDto();
		request.setEmpresaId(arguments[0]);
		request.setFechaSolicitud(LocalDate.parse(arguments[3]));
		request.setMontoSolicitado(new BigDecimal(arguments[1]));
		request.setProductoFinanciero(ProductoFinancieroEnum.valueOf(arguments[2]));
		return request;
	}
	
}
