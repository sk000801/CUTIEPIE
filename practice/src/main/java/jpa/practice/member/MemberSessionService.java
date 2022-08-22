package jpa.practice.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSessionService {

    private final MemberSessionRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void clear() {
        memberRepository.clear();
    }
}
