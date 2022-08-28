//package jpa.practice.member;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Slf4j
//@Repository
//public class MemberSessionRepository {
//
//    private static Map<String, Member> store = new HashMap<>();
//
//    public void save(Member member) {
//        store.put(member.getMemberId(), member);
//    }
//
//    public Optional<Member> findById(String id) {
//        return findAll().stream()
//                .filter(m -> m.getMemberId().equals(id)).findFirst();
//    }
//
//    public List<Member> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    public void clear() {
//        store.clear();
//    }
//}
