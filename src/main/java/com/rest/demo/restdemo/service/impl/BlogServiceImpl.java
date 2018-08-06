package com.rest.demo.restdemo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.demo.restdemo.model.Blog;
import com.rest.demo.restdemo.repo.BlogRepository;
import com.rest.demo.restdemo.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	// save a blog
	public Blog saveBlog(Blog blog) {
		return blogRepository.save(blog);
	}

	// search for all blogs
	public List<Blog> findAllBlogs() {
		return blogRepository.findAll();
	}

	// search for a blog
	public Optional<Blog> findBlogById(Integer id) {
		return blogRepository.findById(id);
	}

	// delete a blog
	public void deleteBlog(Blog blog) {
		blogRepository.delete(blog);
	}

	public String sayHello() {
		return "Hello World!";
	}
}
