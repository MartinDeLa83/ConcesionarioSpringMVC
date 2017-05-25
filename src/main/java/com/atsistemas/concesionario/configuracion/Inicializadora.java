package com.atsistemas.concesionario.configuracion;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class Inicializadora implements WebApplicationInitializer{

	//Cuando se levanta el servidor de aplicaciones se ejecuta este metodo
	/*public void onStartup(ServletContext servletContext) throws ServletException {
		
		//Creamos un objeto que representa el contexto de Spring
		AnnotationConfigWebApplicationContext springContext = 
						new AnnotationConfigWebApplicationContext();
		
		//Configuramos el objeto de contexto de Spring, para que interprete las
		//clases con @Configuration dentro del paquete "com.atsistemas.curso.configuracion"
		springContext
			.setConfigLocation("com.atsistemas.concesionario.configuracion");
		                        
		DispatcherServlet servlet = new DispatcherServlet(springContext);

		Dynamic dynamic = servletContext.addServlet("dispatcherServlet", servlet);

		dynamic.addMapping("/");
		
	}*/
	
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	
    	WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    
        servletContext.addFilter("springSecurityFilterChain", 
        		new DelegatingFilterProxy("springSecurityFilterChain"))
        			.addMappingForUrlPatterns(null, false, "/*");  
        
        
        servletContext.addListener(new HttpSessionEventPublisher());
        
        
    }
    

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.atsistemas.concesionario.configuracion");
        return context;
    }

}
