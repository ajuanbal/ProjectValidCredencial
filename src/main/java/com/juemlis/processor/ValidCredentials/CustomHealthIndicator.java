package com.juemlis.processor.ValidCredentials;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import com.juemlis.processor.ValidCredentials.CedulaService;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    private final CedulaService cedulaService;

    public CustomHealthIndicator(CedulaService cedulaService) {
        this.cedulaService = cedulaService;
    }

    @Override
    public Health health() {
        int counter = cedulaService.getValidationTime();

        if (counter < 1000) {
            return Health.up().withDetail("Mensaje", "Contador en un nivel aceptable").build();
        } else {
            return Health.down().withDetail("Mensaje", "Contador demasiado alto").build();
        }
    }
}
