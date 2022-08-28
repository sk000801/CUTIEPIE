package jpa.practice.member;

import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        memberRepository.join(member);
    }

    public Member findOne(String memberId) {
        return memberRepository.findOne(memberId);
    }

    public Member findName(String name) {
        return memberRepository.findName(name);
    }
    public List<Member> findAll() {
       return memberRepository.findAll();
    }

    public List<Member> search(String name) {
        return memberRepository.searchMember(name);
    }

}
