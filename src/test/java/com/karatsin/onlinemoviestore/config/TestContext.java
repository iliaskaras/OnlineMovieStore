package com.karatsin.onlinemoviestore.config;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.karatsin.onlinemoviestore.rest.services.IAccountService;
import com.karatsin.onlinemoviestore.rest.services.ICustomerService;
import com.karatsin.onlinemoviestore.rest.services.IMovieService;
import com.karatsin.onlinemoviestore.rest.services.IPaymentMethodService;
import com.karatsin.onlinemoviestore.rest.services.IVideoFormatService;

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
        return Mockito.mock(ICustomerService.class);
    }
    @Bean
    public IAccountService accountService() {
        return Mockito.mock(IAccountService.class);
    }
    @Bean
    public IPaymentMethodService paymentMethodService() {
        return Mockito.mock(IPaymentMethodService.class);
    }
    
    @Bean
    public IMovieService movieService() {
        return Mockito.mock(IMovieService.class);
    }
    
    @Bean
    public IVideoFormatService videoFormatService() {
        return Mockito.mock(IVideoFormatService.class);
    }
    
//    @Bean
//    public CustomerService customerService() {
//        return Mockito.mock(CustomerService.class);
//    }

}
