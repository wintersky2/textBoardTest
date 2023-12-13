package org.example.article;

import org.example.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArticleRepository {
    List<Article> articleList = new ArrayList<>();
    int lastArticleId = 1;

    public int save (String title, String content) {
        Article article = new Article(lastArticleId, title, content, Global.getLoginedMember().getId(), Global.nowDateTime());
        articleList.add(article);

        lastArticleId++;

        return article.getId();
    }

    public List<Article> findByAll() {
        List<Map<String, Object>> rows =  Global.getDBConnection().selectRows("select * from article");

        for (Map<String,Object> row : rows){
            Article article = new Article(row);
            articleList.add(article);
        }
        return articleList;
    }

    public int delete(Article article) {
        articleList.remove(article);

        return article.getId();
    }

    public int update(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);

        return article.getId();
    }

    public Article articleFindById(int id) {
        for (int i = 0; i < articleList.size(); i++) {
            if (id == articleList.get(i).getId()) {
                return articleList.get(i);
            }
        }
        return null;
    }
}