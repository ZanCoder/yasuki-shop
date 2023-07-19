package com.poly.Yasuki.handler;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.session.StandardSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//    private final RefreshTokenService refreshTokenService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        this.redirectAfterLogin(request,response,authentication);
    }
    public void redirectAfterLogin(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        boolean hasUserRole = false;
        boolean hasAdminRole = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("user")) {
                hasUserRole = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("admin")) {
                hasAdminRole = true;
                break;
            }
        }

        if (hasUserRole) {
            redirectStrategy.sendRedirect(request, response, "/");
        } else if (hasAdminRole) {
            redirectStrategy.sendRedirect(request, response, "/admin/index");
        } else {
            throw new IllegalStateException();
        }
    }
}