package org.example.member;

import org.example.Global;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    List<Member> memberList = new ArrayList<>();
    int lastMemberId = 1;
    public MemberRepository () {
        Member member1 = new Member(1, "user1", "1234", Global.nowDateTime());
        memberList.add(member1);
        Member member2 = new Member(2, "user2", "1234", Global.nowDateTime());
        memberList.add(member2);
        Member member3 = new Member(3, "user3", "1234", Global.nowDateTime());
        memberList.add(member3);
    }

    public String join (String userId, String password) {
        Member member = new Member(lastMemberId, userId, password, Global.nowDateTime());
        memberList.add(member);

        lastMemberId++;

        return member.getUserId();
    }

    public Member memberFindByUserId (String userId) {
        for (Member member : memberList) {
            if (userId.equals(member.getUserId())) {
                return member;
            }
        }
        return null;
    }
}