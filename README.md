# ProjectValidCredencial

# Validación de Cédula Ecuatoriana

Este microservicio proporciona una implementación para la validación de cédulas ecuatorianas. La lógica de validación sigue los siguientes pasos:

1. **Longitud de la Cédula:**
    - La cédula debe tener exactamente 10 dígitos.

2. **Región Valida:**
    - Extraer los dos primeros dígitos de la izquierda, que representan la región.
    - Verificar que la región obtenida esté en el rango de 1 a 24, que son las regiones válidas en Ecuador.

3. **Formato Numérico:**
    - Verificar que todos los caracteres de la cédula sean numéricos.

4. **Suma de Dígitos Pares:**
    - Sumar los dígitos en las posiciones pares (0, 2, 4, 6, 8).

5. **Suma de Dígitos Impares:**
    - Multiplicar por 2 los dígitos en las posiciones impares (1, 3, 5, 7, 9).
    - Restar 9 si el resultado es mayor a 9.
    - Sumar los resultados de cada posición.

6. **Suma Total:**
    - Sumar la suma de dígitos pares y la suma de dígitos impares.

7. **Primer Dígito de la Suma Total:**
    - Extraer el primer dígito de la suma total.

8. **Decena Inmediata:**
    - Calcular la decena inmediata del primer dígito de la suma total: `(primer_digito + 1) * 10`.

9. **Dígito Validador:**
    - Restar la decena inmediata de la suma total.
    - Si el resultado es 10, el dígito validador es 0.

10. **Comparación con Último Dígito:**
    - Comparar el dígito validador con el último dígito de la cédula.
    - Si son iguales, la cédula es válida; de lo contrario, es incorrecta.

## Uso del Microservicio

Para utilizar el microservicio, realiza una solicitud al endpoint correspondiente con el número de cédula a validar. La respuesta indicará si la cédula es válida o no.

---

Puedes adaptar esta descripción según las características específicas de tu microservicio y agregar información adicional sobre cómo ejecutar, construir y desplegar el servicio.

