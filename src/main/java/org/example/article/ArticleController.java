package org.example.article;

import org.example.Global;

import java.util.List;

public class ArticleController {

    ArticleService articleService;

    public ArticleController () {
        articleService = new ArticleService();
    }

    public void create () {
        if (Global.getLoginedMember() == null) {
            System.out.println("해당기능은 로그인 후 가능합니다.");
            return;
        }

        System.out.printf("제목 : ");
        String title = Global.getScanner().nextLine();
        System.out.printf("내용 : ");
        String content = Global.getScanner().nextLine();

        int id = this.articleService.save(title, content);

        System.out.println(id + "번 게시글이 등록되었습니다.");

    }
    public void list () {
        List<Article> articleList = this.articleService.findByAll();

        System.out.println("번호 / 제목 / 내용 / 작성자 / 등록일");
        System.out.println("--------------------------------------");
        for (Article article : articleList) {
            System.out.printf("%d,   %s,   %s,   %d,   %s\n", article.getId(), article.getTitle(), article.getContent(), article.getMemberId(), article.getRegDate());
        }
    }
    public void delete () {
        if (Global.getLoginedMember() == null) {
            System.out.println("해당기능은 로그인 후 가능합니다.");
            return;
        }

        System.out.println("삭제할 id를 입력하세요.");
        System.out.printf("ID : ");
        int removeId = Integer.parseInt(Global.getScanner().nextLine().trim());

        // 해당 article 객체 받아오기
        Article article = this.articleService.articleFindById(removeId);

        if (article == null) {
            System.out.println("해당 게시글은 존재하지 않습니다.");
            return;
        }

        if (article.getMemberId() != Global.getLoginedMember().getId()) {
            System.out.println("해당 작성자만 삭제가 가능합니다.");
            return;
        }

        int id = this.articleService.delete(article);

        System.out.println(id + "번 게시글이 삭제 되었습니다.");
    }
    public void update () {
        if (Global.getLoginedMember() == null) {
            System.out.println("해당기능은 로그인 후 가능합니다.");
            return;
        }

        System.out.println("수정할 id를 입력하세요.");
        System.out.printf("ID : ");
        int modifyId = Integer.parseInt(Global.getScanner().nextLine().trim());

        // 해당 article 객체 받아오기
        Article article = this.articleService.articleFindById(modifyId);

        if (article == null) {
            System.out.println("해당 게시글은 존재하지 않습니다.");
            return;
        }

        if (article.getMemberId() != Global.getLoginedMember().getId()) {
            System.out.println("해당 작성자만 수정이 가능합니다.");
            return;
        }


        System.out.printf("기존 제목 : %s \n", article.getTitle());
        System.out.printf("수정할 제목 : ");
        String title = Global.getScanner().nextLine();
        System.out.printf("기존 내용 : %s \n", article.getContent());
        System.out.printf("수정할 내용 : ");
        String content = Global.getScanner().nextLine();

        int id = this.articleService.update(article, title, content);

        System.out.println(id + "번 게시글이 수정 되었습니다.");
    }

}