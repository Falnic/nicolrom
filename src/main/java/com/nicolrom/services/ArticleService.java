package com.nicolrom.services;

import com.nicolrom.entities.Article;

import java.util.List;

public interface ArticleService {
    Article getArticleById(Integer id);

    List<Article> getArticlesById(List<Integer> articlesId);

    void updateArticles(List<Article> articles, List<Double> prices);
}
