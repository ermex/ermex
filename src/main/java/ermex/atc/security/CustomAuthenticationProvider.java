/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.security;

import ermex.atc.entidad.Personalatencionusuarios;
import ermex.atc.sesion.PersonalatencionusuariosFacade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private PersonalatencionusuariosFacade ejbFacade;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException 
    {
        String name = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        ejbFacade= new PersonalatencionusuariosFacade();
        Personalatencionusuarios  acceso = ejbFacade.acceso(name, password);
        if (acceso!=null)
        {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths=permisos(acceso);
            return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
        }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
            throw new BadCredentialsException("Unable to auth against third party systems");
        }
    }
    public List<GrantedAuthority> permisos(Personalatencionusuarios usuario)
    {
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        if(usuario.getAtcNormal()!=null){if(usuario.getAtcNormal()==true)
            {
                 grantedAuths.add(new SimpleGrantedAuthority("ROLE_ATC_NORMAL"));
            }}
        if(usuario.getAtcGestores()!=null){if(usuario.getAtcGestores()==true)
            {
                 grantedAuths.add(new SimpleGrantedAuthority("ROLE_ATC_GESTORES"));
            }}
        if(usuario.getAtcRatificacion()!=null){if(usuario.getAtcRatificacion()==true)
            {
                 grantedAuths.add(new SimpleGrantedAuthority("ROLE_ATC_RATIFICACION"));
            }}
        if(usuario.getReportes()!=null){if(usuario.getReportes()==true)
            {
                 grantedAuths.add(new SimpleGrantedAuthority("ROLE_REPORTES"));
            }}
        if(usuario.getUsuarios()!=null){if(usuario.getUsuarios()==true)
            {
                 grantedAuths.add(new SimpleGrantedAuthority("ROLE_USUARIOS"));
            }}
        if(usuario.getManual()!=null){if(usuario.getManual()==true)
            {
                 grantedAuths.add(new SimpleGrantedAuthority("ROLE_MANUAL"));
            }}
        return grantedAuths;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
