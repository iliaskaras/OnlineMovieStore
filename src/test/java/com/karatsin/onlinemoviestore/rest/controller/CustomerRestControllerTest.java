package com.karatsin.onlinemoviestore.rest.controller;

import com.karatsin.onlinemoviestore.config.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.karatsin.onlinemoviestore.config.OnlineMovieStoreConfig;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.util.TestUtil;
import com.karatsin.onlinemoviestore.rest.controller.ICustomerService;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomerNotFoundException;

import java.util.Arrays;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, OnlineMovieStoreConfig.class})
@WebAppConfiguration
public class CustomerRestControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private ICustomerService customerServiceMock;
	
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
    public void get_ExistedCustomer_ByCustomerEmail() throws Exception {
		
        Customer customer = new Customer(1,"firstName","lastName","test@test.com","123123123",18);
        when(customerServiceMock.getCustomerByEmail("test@test.com")).thenReturn(customer);
        
        mockMvc.perform(get("/api/customer/email=/{customerEmail}","test@test.com"))
        	.andExpect(status().isOk())
        	.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.firstName", is("firstName")))
            .andExpect(jsonPath("$.lastName", is("lastName")))
            .andExpect(jsonPath("$.phone", is("123123123")))
            .andExpect(jsonPath("$.age", is(18)))
            .andExpect(jsonPath("$.email", is("test@test.com")));
               
        verify(customerServiceMock, times(1)).getCustomerByEmail("test@test.com");
        verifyNoMoreInteractions(customerServiceMock);

    }
	
	@Test
    public void get_NonExistedCustomer_ByCustomerEmail_ShouldReturnHttpStatusCode404() throws Exception {
		
		when(customerServiceMock.getCustomerByEmail("test@test.com"))
			.thenThrow(new CustomerNotFoundException(""));
        mockMvc.perform(get("/api/customer/email=/{customerEmail}","test@test.com"))
            .andExpect(status().isNotFound());

        verify(customerServiceMock, times(1)).getCustomerByEmail("test@test.com");
        verifyNoMoreInteractions(customerServiceMock);

    }

	@Test
    public void get_CustomerWithMailExist_ShouldReturnHttpStatusCode400() throws Exception {
		
        when(customerServiceMock.customerWithMailExist("test@test.com"))
        	.thenThrow(new CustomerWithEmailExistException(""));
        
        mockMvc.perform(get("/api/customerExistance/email=/{customerEmail}","test@test.com"))
        	.andExpect(status().isBadRequest());
               
        verify(customerServiceMock, times(1)).customerWithMailExist("test@test.com");
        verifyNoMoreInteractions(customerServiceMock);

    }
	
	@Test
    public void get_CustomerWithMailDontExist() throws Exception {
		
        when(customerServiceMock.customerWithMailExist("newtest@test.com"))
        		.thenThrow(new CustomerWithEmailExistException("Customer with email :newtest@test.com, not found!"));

        mockMvc.perform(get("/api/customerExistance/email=/{customerEmail}","newtest@test.com"))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("$.status", is(400)))
	        .andExpect(jsonPath("$.message", is("CustomerRestExceptionHandler exception : Customer with email :newtest@test.com, not found!")));
       
		verify(customerServiceMock, times(1)).customerWithMailExist("newtest@test.com");
		verifyNoMoreInteractions(customerServiceMock);

    }
	
	@Test
    public void delete_CustomerWithThatDontExist_ShouldReturnHttpStatusCode404() throws Exception {

        when(customerServiceMock.getCustomerById(2))
        	.thenThrow(new CustomerNotFoundException("Customer with id :2, not found!"));

        mockMvc.perform(delete("/api/customers/id=/{customerId}",2))
	        .andExpect(status().isNotFound())
	    	.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("$.status", is(404)))
	        .andExpect(jsonPath("$.message", is("CustomerRestExceptionHandler exception : Customer with id :2, not found!")));
       
		verify(customerServiceMock, times(1)).getCustomerById(2);
		verifyNoMoreInteractions(customerServiceMock);

    }
	
	@Test
    public void delete_CustomerWithThatExist_ShouldReturnHttpStatusCode200() throws Exception{
		
		InOrder inOrder = inOrder(customerServiceMock);
		Customer customer = new Customer(1,"firstName","lastName","test@test.com","123123123",18);
		when(customerServiceMock.getCustomerById(1)).thenReturn(customer);
		
        mockMvc.perform(delete("/api/customers/id=/{customerId}",1))
	        .andExpect(status().isOk())
	        .andExpect(content().string("Customer with id: 1, deleted succesfully!"));
       
		verify(customerServiceMock, times(1)).getCustomerById(1);
		inOrder.verify(customerServiceMock).getCustomerById(1);
		inOrder.verify(customerServiceMock).deleteCustomer(1);
		verifyNoMoreInteractions(customerServiceMock);
    }
	
	@Test
    public void delete_CustomerGivingStringAsParameter_ShouldReturnHttpStatusCode400() throws Exception{

		mockMvc.perform(delete("/api/customers/id=/{customerId}","stringInsteadOfInteger"))
	        .andExpect(status().isBadRequest())
	    	.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("$.status", is(400)))
	        .andExpect(jsonPath("$.message", is("general exception : Failed to convert value of type 'java.lang.String' to required type 'int';"
	        		+ " nested exception is java.lang.NumberFormatException: For input string: \"stringInsteadOfInteger\"")));
    
		verifyNoMoreInteractions(customerServiceMock);
    }
	
	
	@Test
    public void get_GetAllCustomers_ShouldReturnHttpStatusCode200() throws Exception{

		Customer customer1 = new Customer(1,"firstName1","lastName1","test1@test.com","123123123",18);
		Customer customer2 = new Customer(2,"firstName2","lastName2","test2@test.com","123123123",18);
		Customer customer3 = new Customer(3,"firstName3","lastName3","test3@test.com","123123123",18);
		Customer customer4 = new Customer(4,"firstName4","lastName4","test4@test.com","123123123",18);
		
		when(customerServiceMock.getCustomers()).thenReturn(Arrays.asList(customer1, customer2, customer3, customer4));
		 
		mockMvc.perform(get("/api/customers/all/"))
	    	 .andExpect(status().isOk())
             .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
             .andExpect(jsonPath("$", hasSize(4)))
             .andExpect(jsonPath("$[0].id", is(1)))
             .andExpect(jsonPath("$[0].firstName", is("firstName1")))
             .andExpect(jsonPath("$[0].lastName", is("lastName1")))
             .andExpect(jsonPath("$[1].firstName", is("firstName2")))
             .andExpect(jsonPath("$[1].lastName", is("lastName2")))
             .andExpect(jsonPath("$[3].firstName", is("firstName4")))
             .andExpect(jsonPath("$[3].lastName", is("lastName4")))
             .andExpect(jsonPath("$[3].email", is("test4@test.com")));
    
		verify(customerServiceMock, times(1)).getCustomers();
		verifyNoMoreInteractions(customerServiceMock);
    }
	
	@Test
    public void get_GetEmptyListOfCustomers_ShouldReturnHttpStatusCode200() throws Exception{

		when(customerServiceMock.getCustomers()).thenReturn(null);
		 
		mockMvc.perform(get("/api/customers/all/"))
	    	 .andExpect(status().isOk());
    
		verify(customerServiceMock, times(1)).getCustomers();
		verifyNoMoreInteractions(customerServiceMock);
    }
	
	@Test
    public void post_AddNewCustomer_ShouldReturnHttpStatusCode200() throws Exception{

	    Customer customer = new Customer(0,"firstName","lastName","test@test.com","123123123",18);
//        when(customerServiceMock.saveCustomer(customer)).thenReturn(customer);
		 
		mockMvc.perform(post("/api/customers/add").contentType(TestUtil.APPLICATION_JSON_UTF8)
			 .content(TestUtil.convertObjectToJsonBytes(customer)))
			 .andExpect(status().isOk())
			 .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		     .andExpect(jsonPath("$.id", is(0)))
             .andExpect(jsonPath("$.firstName", is("firstName")))
             .andExpect(jsonPath("$.lastName", is("lastName")))
             .andExpect(jsonPath("$.email", is("test@test.com")))
             .andExpect(jsonPath("$.phone", is("123123123")))
             .andExpect(jsonPath("$.age", is(18)));
    
		ArgumentCaptor<Customer> customerEntity = ArgumentCaptor.forClass(Customer.class);
        verify(customerServiceMock, times(1)).saveCustomer(customerEntity.capture());
        assertEquals("firstName", customerEntity.getValue().getFirstName());
        assertEquals(0, customerEntity.getValue().getId());
        verifyNoMoreInteractions(customerServiceMock);

    }

	
}
