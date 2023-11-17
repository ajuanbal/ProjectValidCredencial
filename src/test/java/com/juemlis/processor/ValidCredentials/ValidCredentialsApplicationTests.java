package com.juemlis.processor.ValidCredentials;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidCredentialsApplicationTests {

	@Autowired
	private CedulaService cedulaService;

	@Test
	public void testValidateRegionValid() {
		assertTrue(cedulaService.validateRegion(10)); // Aquí puedes poner un número que esté en el rango permitido
	}

	@Test
	public void testValidateRegionInvalid() {
		assertFalse(cedulaService.validateRegion(30)); // Aquí puedes poner un número que no esté en el rango permitido
	}

	@Test
	public void testValidateLengthValid() {
		assertTrue(cedulaService.validateLength(10)); // Aquí puedes poner un número que esté en el rango permitido
	}

	@Test
	public void testValidateLengthInvalid() {
		assertFalse(cedulaService.validateLength(25)); // Aquí puedes poner un número que no esté en el rango permitido
	}

}
