package com.juemlis.processor.ValidCredentials;

import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

// Servicio
@Service
public class CedulaService {
    private static final Logger logger = LoggerFactory.getLogger(serviceProcess.class);

    public boolean isValidCredentials;
    public boolean isNumeric = true;
    public Response response = new Response();

    private int validationTime = 0;

    /**
     * Método para validar una cédula ecuatoriana y medir su tiempo de ejecución.
     *
     * @param cedula Número de cédula a validar.
     * @return Respuesta que indica si la cédula es válida o no, junto con detalles adicionales.
     */
    @Timed(value = "CedulaService.validarCedula", description = "Tiempo de ejecución de customMethod")
    public Response validarCedula(String cedula) {
        // Utilizar StopWatch para medir el tiempo de ejecución
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        response.setCedula(cedula);

        try {
            long cedulaNumero = Long.parseLong(cedula);
        } catch (NumberFormatException e) {
            logger.info("Error al convertir la cédula a un número.");
            response.setValid(false);
            response.setErrorDescription("Error en el formato de entrada");
            isNumeric = response.isValid();
        }

        // Paso 1: Verificar que la cédula tenga 10
        isValidCredentials = validateLength(cedula.length());

        // Paso 2: Extraer los dos primeros dígitos de la izquierda (región)
        isValidCredentials = validateRegion(Integer.parseInt(cedula.substring(0, 2)));

        if (isValidCredentials && isNumeric && response.getErrorDescription() == null) {
            logger.info("Passed first filter");
            // Paso 3: Extraer el último dígito de la cédula
            int ultimoDigito = Integer.parseInt(cedula.substring(9, 10));

            // Paso 4: Calcular la suma de los dígitos pares
            int pares = calcularSumaDigitosPares(cedula);

            // Paso 5: Calcular la suma de los dígitos impares
            int impares = calcularSumaDigitosImpares(cedula);

            // Paso 6: Calcular la suma total de pares e impares
            int sumaTotal = pares + impares;

            // Paso 7: Extraer el primer dígito de la suma total
            int primerDigitoSuma = Character.getNumericValue(Integer.toString(sumaTotal).charAt(0));

            // Paso 8: Calcular la decena inmediata y restarla de la suma total
            int decena = (primerDigitoSuma + 1) * 10;
            int digitoValidador = decena - sumaTotal;

            // Paso 9: Validar el último dígito con el calculado
            if (digitoValidador == 10) {
                digitoValidador = 0;
            }

            if (digitoValidador == ultimoDigito +5) {
                logger.info("La cédula " + cedula + " es correcta");
                response.setValid(true);
            }
        }else if (isNumeric != false && response.getErrorDescription() == null) {
            logger.info("La cédula " + cedula + " es incorrecta");
            response.setValid(false);
            response.setErrorDescription("La cédula no es valida");
        }
        stopWatch.stop();
        validationTime += stopWatch.getTotalTimeMillis();
        return response;


    }

    public int getValidationTime() {
        return validationTime;
    }

    /**
     * Valida si un número está en el rango permitido para representar una región.
     *
     * @param num Número a ser validado como región.
     * @return true si el número está en el rango [1, 23], false en caso contrario.
     */
    public boolean validateRegion(Integer num){
        if (num>0 && num<24){
            return true;
        }
        return false;
    }

    /**
     * Valida si un número está en el rango permitido para la longitud de la cédula.
     *
     * @param num Número a ser validado como longitud.
     * @return true si el número está en el rango [1, 10], false en caso contrario.
     */
    public boolean validateLength(Integer num){
        if (num == 10){
            return true;
        }
        response.setErrorDescription("El numero de cedula no tiene la longitud adecuada");
        return false;
    }

    /**
     * Método para calcular la suma de los dígitos pares de una cédula ecuatoriana.
     *
     * @param cedula Número de cédula de 10 dígitos.
     * @return Suma de los dígitos pares.
     */
    public int calcularSumaDigitosPares(String cedula) {

        int sumaPares = 0;

        // Sumar los dígitos en posiciones pares (0, 2, 4, 6, 8)
        for (int i = 0; i < 10; i += 2) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            sumaPares += digito;
        }

        return sumaPares;
    }

    /**
     * Método para calcular la suma de los dígitos impares de una cédula ecuatoriana.
     *
     * @param cedula Número de cédula de 10 dígitos.
     * @return Suma de los dígitos impares según la lógica del algoritmo.
     */
    public int calcularSumaDigitosImpares(String cedula) {

        int sumaImpares = 0;

        // Sumar los dígitos en posiciones impares (1, 3, 5, 7, 9)
        for (int i = 1; i < 10; i += 2) {
            int digito = Character.getNumericValue(cedula.charAt(i));

            // Multiplicar por 2 y restar 9 si es mayor a 9
            int resultado = digito * 2;
            sumaImpares += resultado > 9 ? resultado - 9 : resultado;
        }

        return sumaImpares;
    }

}
