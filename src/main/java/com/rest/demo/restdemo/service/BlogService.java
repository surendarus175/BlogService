package com.rest.demo.restdemo.service;

import java.util.List;
import java.util.Optional;

import com.rest.demo.restdemo.model.Blog;

public interface BlogService {
	
	// save a blog
	public Blog saveBlog(Blog blog);

	// search for all blogs
	public List<Blog> findAllBlogs();

	// search for a blog
	public Optional<Blog> findBlogById(Integer id);

	// delete a blog
	public void deleteBlog(Blog blog);
	
	public String sayHello(); 

}
