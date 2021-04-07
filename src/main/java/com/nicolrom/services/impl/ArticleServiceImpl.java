package com.nicolrom.services.impl;

import com.nicolrom.dao.ArticleDao;
import com.nicolrom.entities.Article;
import com.nicolrom.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article getArticleById(Integer id) {
        return articleDao.getArticleById(id);
    }

    @Override
    public List<Article> getArticlesById(List<Integer> articlesId) {
        List<Article> articles = new ArrayList<>();
        for (Integer id : articlesId){
            articles.add(getArticleById(id));
        }
        return articles;
    }

    @Override
    public void updateArticles(List<Article> articles, List<Double> prices) {
            for (Article article : articles){
                article.setPrice(prices.get(articles.indexOf(article)));
                articleDao.updateArticle(article);
            }
    }
}
