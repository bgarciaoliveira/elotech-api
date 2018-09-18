package br.garcia.configuration;

import br.garcia.util.Jwt;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws URISyntaxException, IOException {

                String uri = new URI(httpServletRequest.getRequestURI()).normalize().toString();

                //normalize uri
                if(uri.charAt(uri.length() - 1) == '/'){
                    uri = uri.substring(0, uri.length() - 1);
                }

                List<String> noTokenEndpoints = new ArrayList<>();
                noTokenEndpoints.add("/api/colaboradores/auth");
                noTokenEndpoints.add("/api/colaboradores");

                if(!noTokenEndpoints.contains(uri)){
                    String token = httpServletRequest.getHeader("token");
                    String id = httpServletRequest.getHeader("id");


                    if(token != null && id != null && !token.equals("") && !id.equals("")){

                        if(Jwt.verify(token, id)){

                            return true;
                        }
                    }

                    //send badrequest response
                    //o token/id não são válidos ou não foram informados

                    httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    JSONObject response = new JSONObject();
                    response.put("info", "INVALID_TOKEN");

                    httpServletResponse.getWriter().write(response.toString());

                    return false;
                }

                return true;
            }

            @Override
            public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)  {
            }

            @Override
            public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

            }
        });
    }
}