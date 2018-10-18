package com.karatsin.onlinemoviestore.rest.controller;

import com.karatsin.onlinemoviestore.config.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.karatsin.onlinemoviestore.config.OnlineMovieStoreConfig;
import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.util.TestUtil;

import com.karatsin.onlinemoviestore.config.TestContext;
import java.util.Arrays;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, OnlineMovieStoreConfig.class})
//@ContextConfiguration(locations = {"classpath:testContext.xml", "classpath:exampleApplicationContext-web.xml"})
@WebAppConfiguration
public class MovieRestControllerTest {
	
	private MockMvc mockMvc;
	
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
//        Mockito.reset(todoServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	@Test
    public void get_NonExistedMovie_ByMovieID() throws Exception {
        Movie movie = new Movie();

        mockMvc.perform(get("/api/movies/5")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(movie)))
                .andExpect(status().is(404));
//                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
//                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
//                .andExpect(jsonPath("$.fieldErrors[0].path", is("title")))
//                .andExpect(jsonPath("$.fieldErrors[0].message", is("The title cannot be empty.")));

     //   verifyZeroInteractions(todoServiceMock);
    }
	
	@Test
    public void get_ExistedMovie_ByMovieID() throws Exception {
        Movie movie = new Movie();

        mockMvc.perform(get("/api/movies/1")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(movie)))
                .andExpect(status().is(200));
//                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
//                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
//                .andExpect(jsonPath("$.fieldErrors[0].path", is("title")))
//                .andExpect(jsonPath("$.fieldErrors[0].message", is("The title cannot be empty.")));

     //   verifyZeroInteractions(todoServiceMock);
    }
}
