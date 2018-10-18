package com.karatsin.onlinemoviestore.config;

import org.mockito.Mockito;
//import net.petrikainulainen.spring.testmvc.todo.service.TodoService;
import org.mockito.Mockito.*;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.karatsin.onlinemoviestore.dao.ICustomerDAO;
import com.karatsin.onlinemoviestore.rest.controller.CustomerService;
import com.karatsin.onlinemoviestore.rest.controller.ICustomerService;

//@Configuration
public class TestContext {
	
    private static final String MESSAGE_SOURCE_BASE_NAME = "classpath:ValidationMessages";
    
    @Bean
    public MessageSource messageSource() {
    	   ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

    	   messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
    	   messageSource.setDefaultEncoding("UTF-8");
    	   return messageSource;
    }

    @Bean
    public ICustomerService customerService() {
     //   return Mockito.mock(CustomerService.class);
        return Mockito.mock(ICustomerService.class);
    }
    
//    @Bean
//    public ICustomerDAO customerDAO() {
//     //   return Mockito.mock(CustomerService.class);
//        return Mockito.mock(ICustomerDAO.class);
//    }
    
//    @Bean
//    public CustomerService customerService() {
//        return Mockito.mock(CustomerService.class);
//    }

}
