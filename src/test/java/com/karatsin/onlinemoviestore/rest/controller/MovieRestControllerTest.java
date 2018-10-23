package com.karatsin.onlinemoviestore.rest.controller;

import com.karatsin.onlinemoviestore.config.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.karatsin.onlinemoviestore.config.OnlineMovieStoreConfig;
import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.entity.RegistrationWrapper;
import com.karatsin.onlinemoviestore.entity.VideoFormatType;
import com.karatsin.onlinemoviestore.exception.account.AccountNotFoundException;
import com.karatsin.onlinemoviestore.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.exception.movie.MovieNotFoundException;
import com.karatsin.onlinemoviestore.rest.services.ICustomerService;
import com.karatsin.onlinemoviestore.rest.services.IMovieService;
import com.karatsin.onlinemoviestore.rest.services.IVideoFormatService;
import com.karatsin.onlinemoviestore.util.TestUtil;

import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;

import com.karatsin.onlinemoviestore.config.TestContext;
import java.util.Arrays;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, OnlineMovieStoreConfig.class})
//@ContextConfiguration(locations = {"classpath:testContext.xml", "classpath:exampleApplicationContext-web.xml"})
@WebAppConfiguration
public class MovieRestControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private IMovieService movieServiceMock;
	
	@Autowired
	private IVideoFormatService videoFormatServiceMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
    public void setUp() {
		
		Mockito.reset(movieServiceMock);
		Mockito.reset(videoFormatServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	@Test
    public void get_NonExistedMovie_ByMovieID_ShouldReturnStatusCode404() throws Exception {

        when(movieServiceMock.getMovieById(1))
        	.thenThrow(new MovieNotFoundException("Movie with id :1, not found!"));
        
        mockMvc.perform(get("/api/movies/id=/{movieId}",1))
        	.andExpect(status().isNotFound())
	        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("$.status", is(404)))
	        .andExpect(jsonPath("$.message", is("MovieRestExceptionHandler exception : Movie with id :1, not found!")));
	        
        verify(movieServiceMock, times(1)).getMovieById(1);
        verifyNoMoreInteractions(movieServiceMock);
    }
	
	@Test
    public void get_ExistedMovie_ByMovieID_ShouldReturnStatusCode200() throws Exception {

		Movie movie = new Movie.MovieBuilder().setGenreTypeId(1).setId(1)
				.setMovieDescription("description").setMovieTitle("testtitle")
				.setReleaseYear(2018).setRentalPackageId(1).setVideoFormatTypeId(1).build();
	
        when(movieServiceMock.getMovieById(1)).thenReturn(movie);
        
        mockMvc.perform(get("/api/movies/id=/{movieId}",1))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.genreTypeId", is(1)))
        .andExpect(jsonPath("$.movieDescription", is("description")))
        .andExpect(jsonPath("$.movieTitle", is("testtitle")))
        .andExpect(jsonPath("$.rentalPackageId", is(1)))
        .andExpect(jsonPath("$.videoFormatTypeId", is(1)))
        .andExpect(jsonPath("$.releaseYear", is(2018)));
           
	    verify(movieServiceMock, times(1)).getMovieById(1);
	    verifyNoMoreInteractions(movieServiceMock);
    }
	
	@Test
    public void get_listOfMoviesCorrect() throws Exception {
		
		Movie movie1 = new Movie.MovieBuilder().setGenreTypeId(1).setId(1)
				.setMovieDescription("description1").setMovieTitle("testtitle1")
				.setReleaseYear(2018).setRentalPackageId(1).setVideoFormatTypeId(1).build();
		Movie movie2 = new Movie.MovieBuilder().setGenreTypeId(2).setId(2)
				.setMovieDescription("description2").setMovieTitle("testtitle2")
				.setReleaseYear(2018).setRentalPackageId(2).setVideoFormatTypeId(2).build();
        
		when(movieServiceMock.getMovies()).thenReturn(Arrays.asList(movie1, movie2));
        
		mockMvc.perform(get("/api/movies"))
			.andExpect(status().isOk())
	        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("$", hasSize(2)))
	        .andExpect(jsonPath("$[0].id", is(1)))
	        .andExpect(jsonPath("$[0].movieTitle", is("testtitle1")))
	        .andExpect(jsonPath("$[0].movieDescription", is("description1")))
	        .andExpect(jsonPath("$[0].videoFormatTypeId", is(1)))
	        .andExpect(jsonPath("$[0].rentalPackageId", is(1)))
	        .andExpect(jsonPath("$[1].id", is(2)))
	        .andExpect(jsonPath("$[1].movieTitle", is("testtitle2")))
	        .andExpect(jsonPath("$[1].movieDescription", is("description2")))
	        .andExpect(jsonPath("$[1].videoFormatTypeId", is(2)))
	        .andExpect(jsonPath("$[1].rentalPackageId", is(2)));
		
        verify(movieServiceMock, times(1)).getMovies();
        verifyNoMoreInteractions(movieServiceMock);

    }
	
	@Test
    public void get_PlayMovieForm_redirectCorrect() throws Exception {
    
		Movie movie = new Movie.MovieBuilder().setGenreTypeId(1).setId(1)
				.setMovieDescription("description").setMovieTitle("testtitle")
				.setReleaseYear(2018).setRentalPackageId(1).setVideoFormatTypeId(1).build();
	
        when(movieServiceMock.getMovieById(1)).thenReturn(movie);
		
		mockMvc.perform(get("/api/movie/watch/movieId=1/true"))
		    .andExpect(status().isOk())
		    .andExpect(forwardedUrl("/WEB-INF/view/watch_movie_form.jsp"))
		    .andDo(MockMvcResultHandlers.print());
	
		verify(movieServiceMock, times(1)).getMovieById(1);

    }
	
	@Test
    public void get_PlayNonPaidMovie_RedirectToHome() throws Exception {
    
		Movie movie = new Movie.MovieBuilder().setGenreTypeId(1).setId(1)
				.setMovieDescription("description").setMovieTitle("testtitle")
				.setReleaseYear(2018).setRentalPackageId(1).setVideoFormatTypeId(1).build();
	
        when(movieServiceMock.getMovieById(1)).thenReturn(movie);
		
		mockMvc.perform(get("/api/movie/watch/movieId=1/false"))
		    .andExpect(status().isOk())
		    .andExpect(forwardedUrl("/WEB-INF/view/home_logged_in.jsp"))
		    .andDo(MockMvcResultHandlers.print());
	
		verify(movieServiceMock, times(0)).getMovieById(1);

    }
	
	@Test
    public void startPlayingMovie_VideoFormatHDV() throws Exception {
    
		Movie movie = new Movie.MovieBuilder().setGenreTypeId(1).setId(1)
				.setMovieDescription("description").setMovieTitle("testtitle")
				.setReleaseYear(2018).setRentalPackageId(1).setVideoFormatTypeId(1).build();
	
		VideoFormatType videoFormat = new VideoFormatType(1,"HDV");
		
        when(movieServiceMock.getMovieById(1)).thenReturn(movie);
        when(videoFormatServiceMock.getVideoFormatById(1)).thenReturn(videoFormat);

		mockMvc.perform(post("/api/movie/play/id=1"))
		    .andExpect(status().isOk())
		    .andExpect(content().string("Playing testtitle, Decoding and playing HDV video, the movie is : testtitle"));
		    		
		verify(movieServiceMock, times(1)).getMovieById(1);
		verify(videoFormatServiceMock, times(1)).getVideoFormatById(1);

    }
	
	@Test
    public void startPlayingMovie_VideoFormatAVI() throws Exception {
    
		Movie movie = new Movie.MovieBuilder().setGenreTypeId(1).setId(1)
				.setMovieDescription("description").setMovieTitle("testtitle")
				.setReleaseYear(2018).setRentalPackageId(1).setVideoFormatTypeId(2).build();
	
		VideoFormatType videoFormat = new VideoFormatType(2,"AVI");
		
        when(movieServiceMock.getMovieById(1)).thenReturn(movie);
        when(videoFormatServiceMock.getVideoFormatById(2)).thenReturn(videoFormat);

		mockMvc.perform(post("/api/movie/play/id=1"))
		    .andExpect(status().isOk())
		    .andExpect(content().string("Playing testtitle, Decoding and playing AVI video, the movie is : testtitle"));
		    		
		verify(movieServiceMock, times(1)).getMovieById(1);
		verify(videoFormatServiceMock, times(1)).getVideoFormatById(2);

    }
	
	@Test
    public void startPlayingMovie_VideoFormatWMV() throws Exception {
    
		Movie movie = new Movie.MovieBuilder().setGenreTypeId(1).setId(1)
				.setMovieDescription("description").setMovieTitle("testtitle")
				.setReleaseYear(2018).setRentalPackageId(1).setVideoFormatTypeId(3).build();
	
		VideoFormatType videoFormat = new VideoFormatType(3,"WMV");
		
        when(movieServiceMock.getMovieById(1)).thenReturn(movie);
        when(videoFormatServiceMock.getVideoFormatById(3)).thenReturn(videoFormat);

		mockMvc.perform(post("/api/movie/play/id=1"))
		    .andExpect(status().isOk())
		    .andExpect(content().string("Playing testtitle, Decoding and playing WMV video, the movie is : testtitle"));
		    		
		verify(movieServiceMock, times(1)).getMovieById(1);
		verify(videoFormatServiceMock, times(1)).getVideoFormatById(3);

    }
	
	@Test
    public void startPlayingMovie_VideoFormatMP4() throws Exception {
    
		Movie movie = new Movie.MovieBuilder().setGenreTypeId(1).setId(1)
				.setMovieDescription("description").setMovieTitle("testtitle")
				.setReleaseYear(2018).setRentalPackageId(1).setVideoFormatTypeId(4).build();
	
		VideoFormatType videoFormat = new VideoFormatType(4,"MP4");
		
        when(movieServiceMock.getMovieById(1)).thenReturn(movie);
        when(videoFormatServiceMock.getVideoFormatById(4)).thenReturn(videoFormat);

		mockMvc.perform(post("/api/movie/play/id=1"))
		    .andExpect(status().isOk())
		    .andExpect(content().string("Playing testtitle, Decoding and playing MP4 video, the movie is : testtitle"));
		    		
		verify(movieServiceMock, times(1)).getMovieById(1);
		verify(videoFormatServiceMock, times(1)).getVideoFormatById(4);

    }
	
	@Test
    public void startPlayingMovie_VideoFormatMOV() throws Exception {
    
		Movie movie = new Movie.MovieBuilder().setGenreTypeId(1).setId(1)
				.setMovieDescription("description").setMovieTitle("testtitle")
				.setReleaseYear(2018).setRentalPackageId(1).setVideoFormatTypeId(5).build();
	
		VideoFormatType videoFormat = new VideoFormatType(5,"MOV");
		
        when(movieServiceMock.getMovieById(1)).thenReturn(movie);
        when(videoFormatServiceMock.getVideoFormatById(5)).thenReturn(videoFormat);

		mockMvc.perform(post("/api/movie/play/id=1"))
		    .andExpect(status().isOk())
		    .andExpect(content().string("Playing testtitle, Decoding and playing MOV video, the movie is : testtitle"));
		    		
		verify(movieServiceMock, times(1)).getMovieById(1);
		verify(videoFormatServiceMock, times(1)).getVideoFormatById(5);

    }
	

}
