# Ordinaris-risk-evaluation
Aplicacion (dummy) para evaluacion de riesgos en base a reglas:

Los componentes principales del proyecto son:
- Clase OrdenarisRiskEngine que es el motor que evalua las reglas
- Reglas definidas en el paquete: com.ordenaris.riskEngine.rule.impl
- Clases que proveen información que las reglas ocupan que estan en el paquete: com.ordenaris.riskEngine.information.provider.dummy

La aplicación al iniciarse carga las clases de reglas del paquete com.ordenaris.riskEngine.rule.impl y crea una lista de dichas reglas, el orden en el que las reglas se cargan en la lista esta definida por la anotación @Order que debe tener cada clase de regla. Después de cargarse la regla, el motor evalua las reglas para establecer el nivel de riesgo como resultado. Tambien se debe considerar que la aplicación recibe argumentos para poder procesar las reglas.


Pasos a seguir para ejecución del aplicativo:

1. Clonar el repositorio
2. Posicionarse en la carpeta raiz del proyecto (donde se encuentra el archivo pom.xml) y crear el jar mediante el comando maven
   mvn clean package
3. Localizar el archigo jar creado (risk-engine-xxx.jar) en la carpeta target
4. Ejecutar el jar: java -jar risk-engine-xxx.jar [argumentos]
5. La aplicación recibe 4 argumentos: 
   1. Id de la empresa (String)
   2. Monto solicitado (decimal)
   3. Producto financiero, debe ser alguna de estas cadenas: LINEA_OPERATIVA, CREDITO_REVOLVENTE, ARRENDAMIENTO_FINANCIERO
   4. Fecha de solicitud en formato aaaa-MM-dd
6. Ejemplo: java -jar risk-engine-0.0.1-SNAPSHOT.jar BBVA   200.10  LINEA_OPERATIVA  2025-04-18
   
