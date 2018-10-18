package com.karatsin.onlinemoviestore.rest.controller;

import com.karatsin.onlinemoviestore.config.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.karatsin.onlinemoviestore.config.OnlineMovieStoreConfig;
import com.karatsin.onlinemoviestore.dao.ICustomerDAO;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.util.TestUtil;
import com.karatsin.onlinemoviestore.rest.controller.ICustomerService;
import java.util.Arrays;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, OnlineMovieStoreConfig.class})
//@ContextConfiguration(locations = {"classpath:testContext.xml", "classpath:exampleApplicationContext-web.xml"})
@WebAppConfiguration
public class CustomerRestControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private ICustomerService customerServiceMock;
	
//	@Autowired
//	private ICustomerDAO customerServiceMock;

	@Autowired
	private WebApplicationContext webApplicationContext;
	

	@Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(customerServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	@Test
    public void get_NonExistedCustomer_ByCustomerEmail() throws Exception {
        Customer customer = new Customer(1,"firstName","lastName","test@test.com","123123123",18);
        
        when(customerServiceMock.getCustomerByEmail("test@test.com")).thenReturn(customer);
        
        mockMvc.perform(get("/api/customers11/{customerEmail}","test@test.com"))
        		.andExpect(status().isOk())
//        		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                //.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("firstName")))
                .andExpect(jsonPath("$.lastName", is("lastName")))
                .andExpect(jsonPath("$.phone", is("123123123")))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(jsonPath("$.email", is("test@test.com")));
               
        verify(customerServiceMock, times(1)).getCustomerByEmail("test@test.com");
        verifyNoMoreInteractions(customerServiceMock);
//                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
//                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
//                .andExpect(jsonPath("$.fieldErrors[0].path", is("title")))
//                .andExpect(jsonPath("$.fieldErrors[0].message", is("The title cannot be empty.")));

     //   verifyZeroInteractions(todoServiceMock);
    }
	
}
