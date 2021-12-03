package com.higor.banco.exception;

import com.google.gson.Gson;
import com.higor.banco.model.dto.response.ErrorResponseDto;
import com.higor.banco.security.TokenAuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomServletError {

    public static void BankServletError(HttpServletRequest request, HttpServletResponse response, ICustomException exception) {

        PrintWriter errorResponse;

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(401);

            errorResponse = response.getWriter();
            errorResponse.print(new Gson().toJson(new ErrorResponseDto(exception.getCodigo(), exception.getMensagem())));
            errorResponse.flush();
        } catch (IOException ex) {
            response.setStatus(401);
            Logger.getLogger(TokenAuthService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
