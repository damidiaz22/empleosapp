package com.damidiaz.empleos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${empleosapp.ruta.imagenes}")
	private String rutaImgenes;

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/empleos/img-vacantes/"); // Windows
		registry.addResourceHandler("/logos/**").addResourceLocations("file:"+this.rutaImgenes);
	}

}
