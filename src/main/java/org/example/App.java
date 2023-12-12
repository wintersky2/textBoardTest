package org.example;

import lombok.RequiredArgsConstructor;
import org.example.article.Article;
import org.example.article.ArticleController;
import org.example.member.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class App {
    private final ArticleController articleController;

    void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 시스템 시작 ==");


        Member member1 = new Member(1, "user1", "1234", LocalDate.now().toString());
        memberList.add(member1);
        Member member2 = new Member(2, "user2", "1234", LocalDate.now().toString());
        memberList.add(member2);
        Member member3 = new Member(3, "user3", "1234", LocalDate.now().toString());
        memberList.add(member3);

        List<Article> articleList = new ArrayList<>();
        int lastArticleId = 1;

        Member loginedMember = null;

        // loginedMember;
        while (true) {
            System.out.printf("명령) ");
            String command = sc.nextLine().trim();

            if (command.equals("종료")) {
                break;
            } else if (command.equals("등록")) {
                articleController.create();
                if (loginedMember == null) {
                    System.out.println("해당기능은 로그인 후 가능합니다.");
                    continue;
                }


            } else if (command.equals("목록")) {

                articleController.list();

            } else if (command.equals("삭제")) {

                if (loginedMember == null) {
                    System.out.println("해당기능은 로그인 후 가능합니다.");
                    continue;
                }

                articleController.delete();

            } else if (command.equals("수정")) {

                if (loginedMember == null) {
                    System.out.println("해당기능은 로그인 후 가능합니다.");
                    continue;
                }

                articleController.update();

            } else if (command.equals("회원가입")) {

                String userId;
                String password;
                String passwordConfirm;
                LocalDate now = LocalDate.now();

                // 중복 아이디 검증
                while (true) {
                    System.out.printf("아이디 : ");
                    userId = sc.nextLine().trim();
                    boolean isDuplcated = false;
                    for (Member member : memberList) {
                        if (userId.equals(member.getUserId())) {
                            System.out.println("중복 아이디가 존재합니다.");
                            isDuplcated = true;
                        }
                    }
                    // 중복 아이디가 없는 경우
                    if (!isDuplcated) {
                        break;
                    }
                }
                while (true) {
                    System.out.printf("비밀번호 : ");
                    password = sc.nextLine().trim();

                    System.out.printf("비밀번호 확인 : ");
                    passwordConfirm = sc.nextLine().trim();

                    if (password.equals(passwordConfirm)) {
                        break;
                    }

                    System.out.println("비밀번호가 일치하지 않습니다.");
                }


                Member member = new Member(lastMemberId, userId, password, now.toString());
                memberList.add(member);
                System.out.println(userId + "님 가입을 환영합니다.");
                lastMemberId++;

            } else if (command.equals("로그인")) {

                if (loginedMember != null) {
                    System.out.println("현재 로그인 상태입니다.");
                    continue;
                }

                Member checkedMember = null;

                System.out.printf("아이디 : ");
                String userId = sc.nextLine().trim();
                System.out.printf("비밀번호 : ");
                String password = sc.nextLine().trim();

                for (Member member : memberList) {
                    if (userId.equals(member.userId)) {
                        checkedMember = member;
                        break;
                    }
                }

                if (checkedMember == null) {
                    System.out.println("해당 회원이 존재하지 않습니다.");
                    continue;
                } else if (checkedMember.password.equals(password) == false) {
                    System.out.println("비밀번호가 일치 하지 않습니다.");
                    continue;
                }

                loginedMember = checkedMember;

                System.out.println(checkedMember.getUserId() + "님 환영합니다.");
            } else if (command.equals("로그아웃")) {
                if (loginedMember == null) {
                    System.out.println("로그인 상태가 아닙니다.");
                    continue;
                }

                loginedMember = null;
                System.out.println("로그아웃 되었습니다.");
            }
        }
        sc.close();
    }
}
