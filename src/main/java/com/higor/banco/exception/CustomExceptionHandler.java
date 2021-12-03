package com.higor.banco.exception;

import com.higor.banco.model.dto.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleException(CustomRuntimeException ex) {
        return exception(ex);
    }

    private ResponseEntity exception(CustomRuntimeException e) {
        // filtra exceptions de autenticação
        if (e.getCodigoErro() != null && e.getCodigoErro().startsWith("A")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ErrorResponseDto(e.getCodigoErro(), e.getMessage()));
        }

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponseDto(e.getCodigoErro(), e.getMessage()));
    }

}
