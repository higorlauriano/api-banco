package com.higor.banco.security;

import com.google.gson.Gson;
import com.higor.banco.exception.CustomServletError;
import com.higor.banco.exception.enums.EnumAuthenticationException;
import com.higor.banco.model.dto.response.TokenResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TokenAuthService {

    static final long EXPIRATION_TIME = 360000L;
    static final String SECRET = "EnvSecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public TokenResponseDto createTokenResponseDto(String user, String password) {
        final String jwt = Jwts.builder()
                .setSubject(user)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        final TokenResponseDto tokenResponse = new TokenResponseDto();
        tokenResponse.setAccess_token(jwt);
        tokenResponse.setToken_type(TOKEN_PREFIX);
        tokenResponse.setExpires_in(EXPIRATION_TIME);

        return tokenResponse;
    }

    public void createToken(HttpServletResponse response, String user, String password) {
        TokenResponseDto tokenResponse = createTokenResponseDto(user, password);
        String jwtResponse = new Gson().toJson(tokenResponse);

        PrintWriter authResponse;

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            authResponse = response.getWriter();
            authResponse.print(jwtResponse);
            authResponse.flush();
        } catch (IOException ex) {
            response.setStatus(401);
            Logger.getLogger(TokenAuthService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Authentication getAuthentication(HttpServletRequest req, HttpServletResponse res) {
        String token = req.getHeader(HEADER_STRING);

        if (token == null) {
            CustomServletError.BankServletError(req, res, EnumAuthenticationException.TOKEN_NAO_INFORMADO);
            return null;
        }

        try {
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user == null) {
                CustomServletError.BankServletError(req, res, EnumAuthenticationException.USUARIO_NAO_ENCONTRADO);
                return null;
            }

            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        } catch (Exception e) {
            CustomServletError.BankServletError(req, res, EnumAuthenticationException.TOKEN_EXPIRADO);
            return null;
        }
    }

}
