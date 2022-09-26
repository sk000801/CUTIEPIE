package jpa.practice.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberSessionService {

    private final MemberSessionRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }

    public void save_account(MemberAccount memberAccount) {
        memberRepository.save_account(memberAccount);
    }

    public Member findByUID(String id) {
        return memberRepository.findByUID(id);
    }

    public Member findById(String id) {
        return memberRepository.findById(id);
    }

    public MemberAccount findByMemberId(String id) {
        return memberRepository.findByMemberId(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

//    public void clear() {
//        memberRepository.clear();
//    }
}
