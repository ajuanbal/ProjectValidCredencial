package com.juemlis.processor.ValidCredentials;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {
    private String cedula;
    private boolean isValid;
    private String errorDescription;

    public Response() {

    }


    public String getCedula() {
        return cedula;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }


    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}"; // En caso de error, retorna un objeto JSON vacío
        }
    }
}
