package com.karatsin.onlinemoviestore.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * Web App Initializer - All Java Configuration class
 * */
public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Reference to our App configuration class */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { OnlineMovieStoreConfig.class };
	}

	/* Set up of our actual Servlet Mapping, by a simple map everything to a slash 
	 * Slash is the root of this given application context */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	
}
