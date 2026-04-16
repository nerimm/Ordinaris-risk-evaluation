package com.ordenaris.riskEngine.engine;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ordenaris.riskEngine.dto.RequestDto;
import com.ordenaris.riskEngine.dto.catalog.NivelRiesgoEnum;
import com.ordenaris.riskEngine.dto.catalog.ProductoFinancieroEnum;
import com.ordenaris.riskEngine.information.provider.DatosContablesProvider;
import com.ordenaris.riskEngine.information.provider.dummy.HistorialPagosProviderImpl;
import com.ordenaris.riskEngine.information.provider.dummy.VerificacionLegalProviderImpl;
import com.ordenaris.riskEngine.rule.impl.AltaSolicitudVersusVentasRule;
import com.ordenaris.riskEngine.rule.impl.DemandaLegalAbiertaRule;
import com.ordenaris.riskEngine.rule.impl.DeudaActivaRule;
import com.ordenaris.riskEngine.rule.impl.EmpresaNuevaRule;
import com.ordenaris.riskEngine.rule.impl.HistorialExcelenteRule;
import com.ordenaris.riskEngine.rule.impl.ProductoEstrictoRule;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest(classes = {
		OrdenarisRiskEngine.class,
		AltaSolicitudVersusVentasRule.class,
		DemandaLegalAbiertaRule.class,
		DeudaActivaRule.class,
		EmpresaNuevaRule.class,
		HistorialExcelenteRule.class,
		ProductoEstrictoRule.class
})
public class OrdenarisRiskEngineTest {
	
	private static final String EMPRESA_ID = "BBVA";
	
	@Autowired
	private OrdenarisRiskEngine engine;
	
	@MockitoBean
	private DatosContablesProvider datosContablesProvider;
	
	@MockitoBean
	private HistorialPagosProviderImpl historialPagosProvider;
	
	@MockitoBean
	private VerificacionLegalProviderImpl verificacionLegalProvider;
	
	
	@BeforeEach
	public void initAll() {
		when(datosContablesProvider.getPromedioMensualVentas()).thenReturn(new BigDecimal(100));
		when(historialPagosProvider.getDiasDeudaVencida(EMPRESA_ID)).thenReturn(90);
		when(historialPagosProvider.ultimosPagosEnTiempo(EMPRESA_ID)).thenReturn(false);
		when(verificacionLegalProvider.getFechaCreacionEmpresa(EMPRESA_ID)).thenReturn(LocalDate.now().minusMonths(17));
		when(verificacionLegalProvider.existeJuicioMercantilEnCurso(EMPRESA_ID)).thenReturn(true);
	}
	
	@Test
	public void testEngineRules() throws Exception {
		log.info("Test engine rules, engine: {}", engine);
		
		RequestDto request = RequestDto.builder()
				.empresaId(EMPRESA_ID)
				.fechaSolicitud(LocalDate.of(2025, 9, 21))
				.montoSolicitado(new BigDecimal(800))
				.productoFinanciero(ProductoFinancieroEnum.LINEA_OPERATIVA)
		.build();
		
		NivelRiesgoEnum nivelRiesgo = engine.evaluateRules(request);
		assertEquals(NivelRiesgoEnum.ALTO, nivelRiesgo);
	}
	
}
