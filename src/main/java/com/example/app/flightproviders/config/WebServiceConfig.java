package com.example.app.flightproviders.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> dispatcherServletRegistrationBean(ApplicationContext applicationContext) {
        var dispatcherServlet = new MessageDispatcherServlet();
        dispatcherServlet.setApplicationContext(applicationContext);
        dispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(dispatcherServlet, "/ws/*");
    }

    @Bean(name = "flights")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema flightsSchema){
        var wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("FlightsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://petour.com/flights");
        wsdl11Definition.setSchema(flightsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema flightsSchema(){
        return new SimpleXsdSchema(new ClassPathResource("xsd/flights.xsd"));
    }
}
