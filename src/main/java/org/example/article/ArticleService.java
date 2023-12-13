package org.example.article;

import java.util.List;

public class ArticleService {
    private ArticleRepository articleRepository;

    ArticleService () {
        articleRepository = new ArticleRepository();
    }

    public int save (String title, String content) {
        return this.articleRepository.save(title, content);
    }

    public List<Article> findByAll() {
        return this.articleRepository.findByAll();
    }

    public int delete(Article article) {
        return this.articleRepository.delete(article);
    }

    public int update(Article article, String title, String content) {
        return this.articleRepository.update(article, title, content);
    }

    public Article articleFindById(int id) {
        return this.articleRepository.articleFindById(id);
    }
}