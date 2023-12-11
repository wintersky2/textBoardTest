package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 시스템 시작 ==");

        int lastArticleId = 1;

        List<Member> memberList = new ArrayList<>();
        int lastMemberId = 1;
        memberList.add(new Member(1, "user1", "1234", LocalDate.now().toString()));
        memberList.add(new Member(2, "user2", "1234", LocalDate.now().toString()));
        memberList.add(new Member(3, "user3", "1234", LocalDate.now().toString()));

        List<Article> articleList = new ArrayList<>();


        Member loginedMember = null;

        while (true) {
            boolean reqLogin = false;
            System.out.printf("명령) ");
            String command = sc.nextLine().trim();
            if (loginedMember != null) {
                if (command.equals("등록")) {
                    System.out.printf("제목 : ");
                    String title = sc.nextLine();
                    System.out.printf("내용 : ");
                    String content = sc.nextLine();

                    String userId = loginedMember.getUserId();

                    Article article = new Article(lastArticleId, title, content, userId);
                    articleList.add(article);

                    lastArticleId++;
                } else if (command.equals("목록")) {
                    System.out.println("번호 / 제목 / 내용 / 작성자");
                    System.out.println("-------------------");
                    for (Article article : articleList) {
                        System.out.printf("%d /   %s /   %s /   %s \n", article.getId(), article.getTitle(), article.getContent(), article.getUserId());
                    }
                } else if (command.equals("삭제")) {
                    System.out.println("삭제할 id를 입력하세요.");
                    System.out.printf("ID : ");
                    int removeId = Integer.parseInt(sc.nextLine().trim());

                    for (int i = 0; i < articleList.size(); i++) {
                        if (removeId == articleList.get(i).getId()) {
                            articleList.remove(i);
                        }
                    }
                    System.out.println(removeId + "번 게시글이 삭제 되었습니다.");
                } else if (command.equals("수정")) {
                    System.out.println("수정할 id를 입력하세요.");
                    System.out.printf("ID : ");
                    int modifyId = Integer.parseInt(sc.nextLine().trim());

                    for (int i = 0; i < articleList.size(); i++) {
                        if (modifyId == articleList.get(i).getId()) {
                            Article article = articleList.get(i);
                            System.out.printf("기존 제목 : %s \n", article.getTitle());
                            System.out.printf("수정할 제목 : ");
                            String title = sc.nextLine();
                            System.out.printf("기존 내용 : %s \n", article.getContent());
                            System.out.printf("수정할 내용 : ");
                            String content = sc.nextLine();

                            article.setTitle(title);
                            article.setContent(content);
                        }
                    }
                    System.out.println(modifyId + "번 게시글이 수정 되었습니다.");
                }
            }
            if (command.equals("로그아웃")) {
                if (loginedMember == null) {
                    System.out.println("로그인 상태가 아닙니다.");
                } else if (loginedMember != null) {
                    System.out.println("로그아웃 되었습니다.");

                    loginedMember = null;
                }
            }
            if (command.equals("회원가입")) {
                String userId;
                String password;
                String passwordConfirm;
                LocalDate now = LocalDate.now();
                reqLogin = true;
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
            } else if (command.equals("종료")) {
                break;
            }
            if (loginedMember == null && reqLogin != true) {
                System.out.println("로그인을 해주세요");
            }
        }
        sc.close();
    }
}