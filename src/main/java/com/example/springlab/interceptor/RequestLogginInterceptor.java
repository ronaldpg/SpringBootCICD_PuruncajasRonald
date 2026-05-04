package com.example.springlab.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLogginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String authHeader = req.getHeader("Authorization");
        String myToken = "SpringLab_RonaldPuruncajas";

        if (authHeader == null || !authHeader.equals(myToken)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Acceso denegado: Token invalido");
            System.out.println("Bloqueado por seguridad: " + req.getRequestURI());
            return false;
        }

        req.setAttribute("t0", System.currentTimeMillis());
        System.out.println("preHandle (Autorizado): " + req.getMethod() + " " + req.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
        Long t0 = (Long) req.getAttribute("t0");
        long elapsed = (t0 == null) ? -1 : (System.currentTimeMillis() - t0);
        System.out.println("AfterCompletion -> status: " + resp.getStatus() + " Tiempo: " + elapsed + "ms");
    }
}