package com.nicolrom.dao.impl;

import com.nicolrom.dao.ArticleDao;
import com.nicolrom.entities.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("articleDao")
@Transactional
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Article getArticleById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from Article as a where a.idArticle = :id", Article.class);
        query.setParameter("id", id);
        return (Article) query.getSingleResult();
    }

    @Override
    public void updateArticle(Article article) {
        Session session = sessionFactory.getCurrentSession();
        session.update(article);
    }
}
