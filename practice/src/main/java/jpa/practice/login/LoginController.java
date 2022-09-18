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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

        if(b.hasErrors())  {
            b.reject("Program Error", "죄송합니다 오류가 발생했습니다ㅠㅠ");
        }

        if(loginRepository.failId(form.getMemberId()) == null) {
            b.addError(new FieldError("memberId", "form", "회원님 아이디가 틀렸습니다!"));
        }

        Member logMember = loginRepository.login(form.getMemberId(), form.getPw());

        if(logMember == null) {
            b.addError(new FieldError("form", "pw", "회원님 비밀번호가 틀렸습니다!"));
        }

        sessionManager.createSession(logMember, response);

        session.setAttribute("mySessionId", logMember);

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
