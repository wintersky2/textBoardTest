package org.example.article;

import org.example.member.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    List<Member> memberList = new ArrayList<>();
    int lastMemberId = 1;

    public  void create(){
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String content = sc.nextLine();

        LocalDate now = LocalDate.now();

        Article article = new Article(lastArticleId, title, content, loginedMember.getUserId(), now.toString());
        articleList.add(article);

        lastArticleId++;
    }
    public void list(){
        System.out.println("번호 / 제목 / 내용 / 작성자 / 등록일");
        System.out.println("--------------------------------------");
        for (Article article : articleList) {
            System.out.printf("%d,   %s,   %s,   %s,   %s\n", article.getId(), article.getTitle(), article.getContent(), article.getAuthor(), article.getRegDate());
        }
    }
    public void delete(){

        System.out.println("삭제할 id를 입력하세요.");
        System.out.printf("ID : ");
        int removeId = Integer.parseInt(sc.nextLine().trim());

        Article article = null;
        for (int i = 0; i < articleList.size(); i++) {
            if (removeId == articleList.get(i).getId()) {
                article = articleList.get(i);
            }
        }

        if (article == null) {
            System.out.println("해당 게시글은 존재하지 않습니다.");
            continue;
        }

        if (article.getAuthor() != loginedMember.getUserId()) {
            System.out.println("해당 작성자만 삭제가 가능합니다.");
            continue;
        }

        articleList.remove(article);

        System.out.println(removeId + "번 게시글이 삭제 되었습니다.");
    }
    public void update(){

        System.out.println("수정할 id를 입력하세요.");
        System.out.printf("ID : ");
        int modifyId = Integer.parseInt(sc.nextLine().trim());

        Article article = null;
        for (int i = 0; i < articleList.size(); i++) {
            if (modifyId == articleList.get(i).getId()) {
                article = articleList.get(i);
            }
        }

        if (article == null) {
            System.out.println("해당 게시글은 존재하지 않습니다.");
            continue;
        }

        if (article.getAuthor() != loginedMember.getUserId()) {
            System.out.println("해당 작성자만 수정이 가능합니다.");
            continue;
        }


        System.out.printf("기존 제목 : %s \n", article.getTitle());
        System.out.printf("수정할 제목 : ");
        String title = sc.nextLine();
        System.out.printf("기존 내용 : %s \n", article.getContent());
        System.out.printf("수정할 내용 : ");
        String content = sc.nextLine();

        article.setTitle(title);
        article.setContent(content);

        System.out.println(modifyId + "번 게시글이 수정 되었습니다.");
    }
}