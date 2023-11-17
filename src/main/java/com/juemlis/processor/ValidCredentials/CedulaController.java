package com.juemlis.processor.ValidCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Controlador
@RestController
public class CedulaController {
    private static final Logger logger = LoggerFactory.getLogger(serviceProcess.class);


    @Autowired
    private CedulaService cedulaService;

    @GetMapping("/validar-cedula/{cedula}")
    public Response validarCedula(@PathVariable String cedula) {
        logger.info("------ BEGIN REQUEST ----");
        logger.info("data: " + cedula);
        Response response = cedulaService.validarCedula(cedula);
        logger.info("validarCedula: " + response.getCedula());
        if (response.isValid()) {
            return response;
        } else {
            return response;
        }
    }
}

