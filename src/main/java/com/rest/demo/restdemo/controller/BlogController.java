package com.rest.demo.restdemo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.demo.restdemo.exception.BlogNotFoundException;
import com.rest.demo.restdemo.model.Blog;
import com.rest.demo.restdemo.service.BlogService;

@RestController
@RequestMapping("/myapi")
public class BlogController {

	@Autowired
	private BlogService blogService;

	// get all blogs
	@GetMapping("/blog")
	public List<Blog> getAllBlogs() {
		return blogService.findAllBlogs();
	}

	// get a specific blog by id
	@GetMapping("/blog/{id}")
	public Blog getBlog(@PathVariable int id) {
		return blogService.findBlogById(id)
				.orElseThrow(() -> new BlogNotFoundException("id - Not Found" + id));
	}
	
	/*@GetMapping("/blog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable int id) {
		return new ResponseEntity<Blog>(blogService.findBlogById(id), HttpStatus.OK);
	}*/

	// save a blog
	@PostMapping("/blog")
	public ResponseEntity<Blog> createUser(@Valid @RequestBody Blog blog) {
		Blog savedUser = blogService.saveBlog(blog);

		// CREATED
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					   .buildAndExpand(savedUser.getId())
				       .toUri();

		return ResponseEntity.created(location).build();
	}

	// update a blog
	@PutMapping("/blog/{id}")
	public ResponseEntity<Blog> update(@Valid @PathVariable int id, @RequestBody Blog blogDetails) {
		return blogService.findBlogById(id).map(blog -> modifyBlog(blogDetails, blog))
				.map(blogService::saveBlog)
				.map(blog -> ResponseEntity.ok().body(blog))
				.orElseGet(() -> ResponseEntity.badRequest().build());
	}

	private Blog modifyBlog(Blog blogDetails, Blog blog) {
		blog.setTitle(blogDetails.getTitle());
		blog.setContent(blogDetails.getContent());
		return blog;
	}

	// delete a blog
	@DeleteMapping("blog/{id}")
	public void deleteUser(@PathVariable int id) {
		blogService.findBlogById(id)
				   .ifPresent(blog -> blogService.deleteBlog(blog));
	}
	
	@GetMapping("/hello")
	public String sayHello() {
		return blogService.sayHello();
	}
}
