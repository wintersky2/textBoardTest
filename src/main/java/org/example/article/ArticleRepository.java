package org.example.article;

import org.example.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArticleRepository {
    List<Article> articleList = new ArrayList<>();

    public int save(String title, String content) {

        String sql = String.format("insert into article set title = '%s', content = '%s', memberId = %d, regDate = NOW()", title, content, Global.getLoginedMember().getId());
        int id = Global.getDBConnection().insert(sql);

        return id;
    }

    public List<Article> findByAll() {
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows("select * from article");

        for (Map<String, Object> row : rows) {
            Article article = new Article(row);
            articleList.add(article);
        }
        return articleList;
    }

    public int delete(Article article) {

        String sql = String.format("delete from article where id = %d", article.getId());
        Global.getDBConnection().delete(sql);

        return article.getId();
    }

    public int update(Article article, String title, String content) {

        String sql = String.format("UPDATE article SET title = '%s',content='%s' WHERE id = %d", title, content, article.getId());
        Global.getDBConnection().update(sql);

        return article.getId();
    }

    public Article articleFindById(int id) {

        List<Article> articleList = findByAll();

        for (int i = 0; i < articleList.size(); i++) {
            if (id == articleList.get(i).getId()) {
                return articleList.get(i);
            }
        }
        return null;
    }
}