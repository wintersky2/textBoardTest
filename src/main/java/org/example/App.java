package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    App() {
        Scanner sc = new Scanner(System.in);
        List<Member> memberList = new ArrayList<>();
        int lastMemberId = 1;

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
                if (loginedMember == null) {
                    System.out.println("해당기능은 로그인 후 가능합니다.");
                    continue;
                }

                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String content = sc.nextLine();

                LocalDate now = LocalDate.now();

                Article article = new Article(lastArticleId, title, content, loginedMember.getUserId(), now.toString());
                articleList.add(article);

                lastArticleId++;
            } else if (command.equals("목록")) {
                System.out.println("번호 / 제목 / 내용 / 작성자 / 등록일");
                System.out.println("--------------------------------------");
                for (Article article : articleList) {
                    System.out.printf("%d,   %s,   %s,   %s,   %s\n", article.getId(), article.getTitle(), article.getContent(), article.getAuthor(), article.getRegDate());
                }
            } else if (command.equals("삭제")) {
                if (loginedMember == null) {
                    System.out.println("해당기능은 로그인 후 가능합니다.");
                    continue;
                }

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
            } else if (command.equals("수정")) {
                if (loginedMember == null) {
                    System.out.println("해당기능은 로그인 후 가능합니다.");
                    continue;
                }

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
    }
}
