package jpa.practice.login;

import jpa.practice.SessionManager;
import jpa.practice.member.Member;
import jpa.practice.member.MemberSessionService;
import jpa.practice.member.MemberStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@Slf4j
@RequiredArgsConstructor
@Transactional
public class LoginController {

    private final LoginRepository loginRepository;
    private final SessionManager sessionManager;

    private Map<String, Object> store = new ConcurrentHashMap<>();

//    @GetMapping("/members/login")
//    //@CookieValue(name="memberId", required = false) String id
//    public String login(HttpSession session, HttpServletRequest request, Model model) {
//        Member logMember = (Member) sessionManager.getSession(request);
//        if (logMember == null) return "members/loginMember";
//        model.addAttribute("member", logMember);
//        session.setAttribute("mySessionId", logMember);
//
//        String a = null;
//
//        if(logMember.getStatus() == MemberStatus.member) {
//            a = "mainPage2";
//        }
//
//        if(logMember.getStatus() == MemberStatus.admin) {
//            a = "mainPage3";
//        }
//
//
//        return a;
//    }

    @PostMapping("/members/login")
    public void login2(@Valid @ModelAttribute LoginForm form, BindingResult b
                , HttpServletResponse response, HttpSession session) {

        if(b.hasErrors())  b.reject("Program Error", "죄송합니다 오류가 발생했습니다ㅠㅠ");

        Member logMember = loginRepository.login(form.getMemberId(), form.getPw());

        sessionManager.createSession(logMember, response);

        session.setAttribute("mySessionId", logMember);
        // 여기서부터 야무지게 안들어간다.. 왜 member가 안들어갈까
        // 매핑관계 문제인지 값이 전달이 제대로 안되는지 봐야할 거 같다

        if(logMember == null) {
            b.reject("loginFail", "아이디 혹은 비밀번호가 틀림!");
        }


    }

    @PostMapping("/members/logout")
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        sessionManager.expire(request);
//        HttpSession session = request.getSession(false);
//        if(session != null) {
//            session.invalidate();
//        }
    }
}
