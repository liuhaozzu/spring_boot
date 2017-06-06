package com.liuhaozzu.springboot.readinglist.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liuhaozzu.springboot.readinglist.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, String> {

}
