package org.example.member;

import org.example.Global;

public class MemberService {


    MemberRepository memberRepository;

    MemberService () {
        memberRepository = new MemberRepository();
    }

    public String join (String userId, String password) {
        return this.memberRepository.join(userId, password);
    }


    public Member memberFindByUserId(String userId) {
        return this.memberRepository.memberFindByUserId(userId);
    }

    public void login(Member checkedMember) {
        Global.setLoginedMember(checkedMember);
    }

    public void logout() {
        Global.setLoginedMember(null);
    }
}