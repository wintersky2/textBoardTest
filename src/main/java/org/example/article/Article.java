package org.example.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@AllArgsConstructor
@Getter
@Setter
public class Article {
    private int id;
    private String title;
    private String content;
    private int memberId;
    private String regDate;

    Article(Map<String, Object> row){
        this.id = (int)row.get("id");
        this.title = (String)row.get("title");
        this.content = (String)row.get("content");
        this.memberId = (int)row.get("memberId");
        this.regDate = row.get("regDate").toString();
    }
}