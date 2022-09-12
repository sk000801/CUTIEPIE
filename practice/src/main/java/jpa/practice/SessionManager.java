package jpa.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private static final String SESSION_COOKIE = "mySessionId";

    private Map<String, Object> store = new ConcurrentHashMap<>();

    public void createSession(Object value, HttpServletResponse response) {
        // 데이터를 생성하는 세션.. 따라서 쿠키에 넣어주는 반응을 해줘야함..?
        // 세션 id를 생성하고 값을 세션에 저장
        String sId = UUID.randomUUID().toString();
        store.put(sId, value);

        //쿠키 생성
        Cookie myCookie = new Cookie(SESSION_COOKIE, sId);
        response.addCookie(myCookie);
    }


    //세션을 조회하는 코드
    public Object getSession(HttpServletRequest request) {
        Cookie myCookie = findCookie(SESSION_COOKIE, request);
        if(myCookie == null) return null;
        return store.get(myCookie.getValue());
    }

    public void expire(HttpServletRequest request) {
        // 저장된 데이터를 지우는 세션
        Cookie myCookie = findCookie(SESSION_COOKIE, request);
        if(myCookie != null) {
            store.remove(myCookie.getValue());
        }
    }

    public Cookie findCookie(String name, HttpServletRequest request) {

        if(request.getCookies() == null) return null;

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(name))
                .findAny().orElse(null);
    }
}
