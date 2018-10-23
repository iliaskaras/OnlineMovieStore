package com.karatsin.onlinemoviestore.rest.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.florianlopes.*;
import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.karatsin.onlinemoviestore.config.OnlineMovieStoreConfig;
import com.karatsin.onlinemoviestore.config.TestContext;
import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.entity.RegistrationWrapper;
import com.karatsin.onlinemoviestore.exception.account.AccountNotFoundException;
import com.karatsin.onlinemoviestore.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.rest.services.IAccountService;
import com.karatsin.onlinemoviestore.rest.services.ICustomerService;
import com.karatsin.onlinemoviestore.rest.services.IPaymentMethodService;
import com.karatsin.onlinemoviestore.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, OnlineMovieStoreConfig.class})
@WebAppConfiguration
public class AccountRestControlletTest {

	private MockMvc mockMvc;
	
	@Autowired
	private ICustomerService customerServiceMock;
	@Autowired
	private IAccountService accountServiceMock;
	@Autowired
	private IPaymentMethodService paymentMethodServiceMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(customerServiceMock);
        Mockito.reset(accountServiceMock);
        //Mockito.reset(paymentMethodServiceMock);
        
        /** For the @ModelAttribute inside AccountRegistrationRestController*/
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        PaymentMethod paymentMethod1 = new PaymentMethod(1,"Debit card");
        PaymentMethod paymentMethod2 = new PaymentMethod(2,"Debit card");
        paymentMethods.add(paymentMethod1);
        paymentMethods.add(paymentMethod2);
        when(paymentMethodServiceMock.getPaymentMethods()).thenReturn(paymentMethods);
        
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	@Test
    public void get_ExistedAccount_ById() throws Exception {
		
        Customer customer = new  Customer.CustomerBuilder()
				.setAge(18).setEmail("test@test.com")
				.setFirstName("firstName").setId(1)
				.setLastName("lastName").setPhone("123123123").build();
        Account account = new  Account.AccountBuilder()
				.setCustomer(customer).setId(1)
				.setPassword("password")
				.setUsername("username")
				.setPaymentMethodId(1).build();
        
        when(accountServiceMock.getAccountById(1)).thenReturn(account);
        
        mockMvc.perform(get("/api/accounts/id=/{accountId}",1))
        	.andExpect(status().isOk())
        	.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.username", is("username")))
            .andExpect(jsonPath("$.password", is("password")))
            .andExpect(jsonPath("$.customer.firstName", is("firstName")))
            .andExpect(jsonPath("$.customer.lastName", is("lastName")))
            .andExpect(jsonPath("$.customer.email", is("test@test.com")))
            .andExpect(jsonPath("$.customer.phone", is("123123123")))
            .andExpect(jsonPath("$.customer.age", is(18)));
               
        verify(accountServiceMock, times(1)).getAccountById(1);
        verifyNoMoreInteractions(accountServiceMock);

    }
	
	@Test
    public void get_NonExistedAccount_ById__ShouldReturnHttpStatusCode404() throws Exception {
	
        when(accountServiceMock.getAccountById(1))
        	.thenThrow(new AccountNotFoundException("Account with id :1, not found!"));
        
        mockMvc.perform(get("/api/accounts/id=/{accountId}",1))
        	.andExpect(status().isNotFound())
	        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("$.status", is(404)))
	        .andExpect(jsonPath("$.message", is("AccountRestExceptionHandler exception : Account with id :1, not found!")));
	        
        verify(accountServiceMock, times(1)).getAccountById(1);
        verifyNoMoreInteractions(accountServiceMock);

    }
	
	@Test
    public void getAccount_WrongParameter__ShouldReturnHttpStatusCode400() throws Exception {
	    
        mockMvc.perform(get("/api/accounts/id=/{accountId}","shouldBeInteger"))
        	.andExpect(status().isBadRequest())
	        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("$.status", is(400)))
	        .andExpect(jsonPath("$.message", is("general exception : Failed to convert value of type 'java.lang.String' to required type 'int';"
	        		+ " nested exception is java.lang.NumberFormatException: For input string: \"shouldBeInteger\"")));
	        
        verifyNoMoreInteractions(accountServiceMock);

    }
	
	@Test
    public void put_AddNewCustomer_ShouldReturnHttpStatusCode200() throws Exception{

        Customer customer = new  Customer.CustomerBuilder()
				.setAge(18).setEmail("test@test.com")
				.setFirstName("firstName").setId(1)
				.setLastName("lastName").setPhone("123123123").build();
        Account account = new  Account.AccountBuilder()
				.setCustomer(customer).setId(1)
				.setPassword("password")
				.setUsername("username")
				.setPaymentMethodId(1).build();
        
		mockMvc.perform(put("/api/accounts/save/").contentType(TestUtil.APPLICATION_JSON_UTF8)
			 .content(TestUtil.convertObjectToJsonBytes(account)))
			 .andExpect(status().isOk())
			 .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
			 .andExpect(jsonPath("$.id", is(1)))
	         .andExpect(jsonPath("$.username", is("username")))
	         .andExpect(jsonPath("$.password", is("password")))
	         .andExpect(jsonPath("$.customer.firstName", is("firstName")))
	         .andExpect(jsonPath("$.customer.lastName", is("lastName")))
	         .andExpect(jsonPath("$.customer.email", is("test@test.com")))
	         .andExpect(jsonPath("$.customer.phone", is("123123123")))
	         .andExpect(jsonPath("$.paymentMethodId", is(1)))
	         .andExpect(jsonPath("$.customer.age", is(18)));
    
		ArgumentCaptor<Account> accountEntity = ArgumentCaptor.forClass(Account.class);
        verify(accountServiceMock, times(1)).saveAccount(accountEntity.capture());
        assertEquals("username", accountEntity.getValue().getUsername());
        assertEquals(1, accountEntity.getValue().getId());
        verifyNoMoreInteractions(accountServiceMock);

    }
	
	@Test
    public void delete_AccountThatDontExist_ShouldReturnHttpStatusCode404() throws Exception {

        when(accountServiceMock.getAccountById(2))
        	.thenThrow(new AccountNotFoundException("Account with id :2, not found!"));

        mockMvc.perform(delete("/api/account/delete/").param("accountId","2"))
	        .andExpect(status().isNotFound())
	    	.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("$.status", is(404)))
	        .andExpect(jsonPath("$.message", is("AccountRestExceptionHandler exception : Account with id :2, not found!")));
       
		verify(accountServiceMock, times(1)).getAccountById(2);
		verifyNoMoreInteractions(accountServiceMock);

    }
	
	@Test
    public void delete_AccountThatExist_ShouldReturnHttpStatusCode200() throws Exception {

		InOrder inOrder = inOrder(accountServiceMock,customerServiceMock);
        Customer customer = new  Customer.CustomerBuilder()
				.setAge(18).setEmail("test@test.com")
				.setFirstName("firstName").setId(1)
				.setLastName("lastName").setPhone("123123123").build();
        Account account = new  Account.AccountBuilder()
				.setCustomer(customer).setId(1)
				.setPassword("password")
				.setUsername("username")
				.setPaymentMethodId(1).build();
        
		when(accountServiceMock.getAccountById(1)).thenReturn(account);
		
        mockMvc.perform(delete("/api/account/delete/").param("accountId","1"))
	        .andExpect(status().isOk())
	        .andExpect(content().string("Account with id: 1 and Customer with id: 1, deleted succesfully!"));
       
		verify(accountServiceMock, times(1)).getAccountById(1);
		inOrder.verify(accountServiceMock).getAccountById(1);
		inOrder.verify(customerServiceMock).deleteCustomer(1);
		inOrder.verify(accountServiceMock).deleteAccount(1);
		verifyNoMoreInteractions(accountServiceMock);
		verifyNoMoreInteractions(customerServiceMock);
    }
	
	@Test
    public void post_RegisterAlreadyRegisteredCustomer_redirectWithEmailError() throws Exception {

		PaymentMethod paymentMethod = new PaymentMethod(1,"Credit card");
        Customer customer = new  Customer.CustomerBuilder()
				.setAge(18).setEmail("test@test.com")
				.setFirstName("firstName").setId(1)
				.setLastName("lastName").setPhone("123123123").build();
        Account account = new  Account.AccountBuilder()
				.setCustomer(customer).setId(1)
				.setPassword("password")
				.setUsername("username")
				.setPaymentMethodId(1).build();
        
        RegistrationWrapper registrationWrapper = new RegistrationWrapper(account, customer, paymentMethod);
        
		when(customerServiceMock.customerWithMailExist("test@test.com"))
			.thenThrow(new CustomerWithEmailExistException("Customer with email :newtest@test.com, not found!"));
		
		mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/api/account/registration", registrationWrapper)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		    .andExpect(status().isOk())
		    .andExpect(forwardedUrl("/WEB-INF/view/registration_form.jsp"))
		    .andDo(MockMvcResultHandlers.print())
		    .andExpect(model().hasErrors())
		    .andExpect(model().errorCount(1))
		    .andExpect(model().attributeHasFieldErrorCode("registrationWrapper", "customer.email", "errors.signup.email"));
	
		verify(customerServiceMock, times(1)).customerWithMailExist("test@test.com");

    }
	
	@Test
    public void post_RegisterUserAccount_ShouldReturnHttpStatusCode200() throws Exception {
		PaymentMethod paymentMethod = new PaymentMethod(1,"Credit card");
		Customer customer = new  Customer.CustomerBuilder()
				.setAge(18).setEmail("test@test.com")
				.setFirstName("firstName").setId(1)
				.setLastName("lastName").setPhone("123123123").build();
        Account account = new  Account.AccountBuilder()
				.setCustomer(customer).setId(1)
				.setPassword("password")
				.setUsername("username")
				.setPaymentMethodId(1).build();
        
        RegistrationWrapper registrationWrapper = new RegistrationWrapper(account, customer, paymentMethod);
        
        when(paymentMethodServiceMock.getPaymentMethodByType("Credit card"))
			.thenReturn(paymentMethod);
        
		mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/api/account/registration", registrationWrapper)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		    .andExpect(status().isOk())
		    .andExpect(forwardedUrl("/WEB-INF/view/home_logged_in.jsp"))
		    .andDo(MockMvcResultHandlers.print())
		    .andExpect(model().errorCount(0));
		
		verify(paymentMethodServiceMock, times(1)).getPaymentMethodByType("Credit card");
    }
	
	@Test
    public void get_listOfAccountsCorrect() throws Exception {
		
		Customer customer1 = new  Customer.CustomerBuilder()
				.setAge(18).setEmail("test1@test1.com")
				.setFirstName("firstname1").setId(1)
				.setLastName("lastname1").setPhone("123123123").build();
		Customer customer2 = new  Customer.CustomerBuilder()
				.setAge(18).setEmail("test1@test1.com")
				.setFirstName("firstname2").setId(2)
				.setLastName("lastname2").setPhone("123123123").build();
		Account account1 = new  Account.AccountBuilder()
				.setCustomer(customer1).setId(1)
				.setPassword("password1")
				.setUsername("username1")
				.setPaymentMethodId(1).build();
        Account account2 = new  Account.AccountBuilder()
				.setCustomer(customer2).setId(2)
				.setPassword("password2")
				.setUsername("username2")
				.setPaymentMethodId(1).build();
        
		when(accountServiceMock.getAccounts()).thenReturn(Arrays.asList(account1, account2));
        
		mockMvc.perform(get("/api/accounts/all/"))
			.andExpect(status().isOk())
	        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("$", hasSize(2)))
	        .andExpect(jsonPath("$[0].id", is(1)))
	        .andExpect(jsonPath("$[0].username", is("username1")))
	        .andExpect(jsonPath("$[0].password", is("password1")))
	        .andExpect(jsonPath("$[0].customer.firstName", is("firstname1")))
	        .andExpect(jsonPath("$[0].password", is("password1")))
	        .andExpect(jsonPath("$[1].id", is(2)))
	        .andExpect(jsonPath("$[1].username", is("username2")))
	        .andExpect(jsonPath("$[1].password", is("password2")))
	        .andExpect(jsonPath("$[1].customer.firstName", is("firstname2")));
		
        verify(accountServiceMock, times(1)).getAccounts();
        verifyNoMoreInteractions(accountServiceMock);

    }
	
	
}