package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<WiseSaying> wiseSayingList = new ArrayList<>();

        int idCount = 1;

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine();
            WiseSaying ws = new WiseSaying();

            if (command.equals("등록")) {

                System.out.print("명언 : ");
                String content = sc.nextLine();

                System.out.print("작가 : ");
                String author = sc.nextLine();

                ws.wiseSaying(idCount, content, author);
                wiseSayingList.add(ws);

                idCount++;
            } else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (WiseSaying wiseSaying : wiseSayingList) {
                    System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
                }
            } else if (command.equals("삭제")) {
                System.out.print("id 입력 : ");
                int id = sc.nextInt();
                for (int i = 0; i < wiseSayingList.size(); i++) {
                    if (wiseSayingList.get(i).getId() == id) {
                        wiseSayingList.remove(i);
                    }
                }
                sc.nextLine();
            } else if (command.equals("수정")) {
                System.out.print("id 입력 : ");
                int id = sc.nextInt();
                sc.nextLine();

                int listID = 1;
                for (int i = 0; i < wiseSayingList.size(); i++) {
                    if (wiseSayingList.get(i).getId() == id) {
                        listID = i;
                    }
                }
                System.out.println("기존 명언 : " + wiseSayingList.get(listID).getContent());
                System.out.println("명언 : ");
                String newContent = sc.nextLine();

                System.out.println("기존 작가 : " + wiseSayingList.get(listID).getAuthor());
                System.out.print("작가 : ");
                String newAuthor = sc.nextLine();

                wiseSayingList.get(listID).setContent(newContent);
                wiseSayingList.get(listID).setAuthor(newAuthor);
            } else if (command.equals("종료")) {
                System.out.println("종료");
                break;
            }
        }
    }
}
