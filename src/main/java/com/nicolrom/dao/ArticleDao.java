package com.nicolrom.dao;

import com.nicolrom.entities.Article;

public interface ArticleDao {

    Article getArticleById(Integer id);

    void updateArticle(Article article);
}
