package com.juemlis.processor.ValidCredentials;

import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

@Service
public class serviceProcess {

    @Timed(value = "myService.customMethod", description = "Tiempo de ejecución de customMethod")
    public void customMethod() {
        // Lógica del método
    }
}
