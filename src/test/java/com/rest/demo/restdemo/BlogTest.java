package com.rest.demo.restdemo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.demo.restdemo.controller.BlogController;
import com.rest.demo.restdemo.service.BlogService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BlogTest {

	private MockMvc mockMvc;

	@Mock
	private BlogService blogService;

	@InjectMocks
	private BlogController blogController;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
	}
	
	@Test
	public void testHello() throws Exception {
		when(blogService.sayHello()).thenReturn("Hello World!");
		
		mockMvc.perform(get("/myapi/hello"))
			   .andExpect(status().isOk())
			   .andExpect((ResultMatcher) content().string("Hello World!"));
		
		verify(blogService).sayHello();
	}

	@Test
	public void testGetABlogById() throws Exception {
		mockMvc.perform(get("/myapi/blog/1")
			   .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk());
			   /*.andExpect(jsonPath("$.title", org.hamcrest.Matchers.is("Java8")))
			   .andExpect(jsonPath("$.content", org.hamcrest.Matchers.is("Stream API")))
			   .andExpect(jsonPath("$.*", org.hamcrest.Matchers.hasSize(3)));*/
	}
	
	@Test
	public void testPost() throws Exception {
		String json = "{\n" +
					  " \"title\": \"b1\", \n " +
					  " \"content\": \"c1\" \n " +
					  "}";
		
		
		mockMvc.perform(post("/myapi/blog")
			   .contentType(MediaType.APPLICATION_JSON).content(json))
		       .andExpect(status().isCreated())
		       .andReturn();
	}
	
	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(delete("/myapi/blog/6")
			   .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk());
	}
	

}
