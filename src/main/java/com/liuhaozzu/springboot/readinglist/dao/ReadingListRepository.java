package com.liuhaozzu.springboot.readinglist.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liuhaozzu.springboot.readinglist.entity.Book;


public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);
}
