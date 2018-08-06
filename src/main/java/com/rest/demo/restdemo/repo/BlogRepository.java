package com.rest.demo.restdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.restdemo.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

}
