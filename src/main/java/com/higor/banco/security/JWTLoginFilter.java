package com.higor.banco.security;

import com.higor.banco.exception.CustomServletError;
import com.higor.banco.exception.enums.EnumAuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    static final String TOKEN_PREFIX = "Authorization";
    static final String TOKEN_API = "Basic bDdlNjk5YjQ2ZDliYWI0N2NiYjkwNDll"
            + "NWQyMDM3ZTNlYzo1NTRkYjExY2Y3MzI0ZGM5ODJjMzY0YjgwOGE3ODUxYQ==";

    protected JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        String token = req.getHeader(TOKEN_PREFIX);

        if (token == null) {
            res.setStatus(401);
            return null;
        }

        if (!token.equals(TOKEN_API)) {
            res.setStatus(401);
            return null;
        }

        if (req.getParameter("usuario") == null
                || req.getParameter("senha") == null) {
            CustomServletError.BankServletError(req, res, EnumAuthenticationException.DADOS_INVALIDOS);
            return null;
        }

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getParameter("usuario"),
                        req.getParameter("senha")
                )
        );
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain filterChain, Authentication auth) {
        TokenAuthService newToken = new TokenAuthService();
        res.setStatus(201);
        newToken.createToken(res, req.getParameter("usuario"), req.getParameter("senha"));
    }

}
