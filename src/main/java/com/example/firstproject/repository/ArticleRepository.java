package com.example.firstproject.repository;


import org.springframework.data.repository.CrudRepository;
import com.example.firstproject.entity.Article;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article , Long> {
    @Override
    ArrayList<Article> findAll();
}
