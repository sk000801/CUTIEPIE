package jpa.practice.login;

import jpa.practice.SessionManager;
import jpa.practice.member.Member;
import jpa.practice.member.MemberSessionService;
import jpa.practice.member.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@AllArgsConstructor
@Transactional
public class LoginController {

    @Autowired
    private final HttpServletRequest request;
    @Autowired
    private HttpSession session;
    private final LoginRepository loginRepository;
    private final SessionManager sessionManager;

    @GetMapping("/members/login")
    public String login() {
        return "members/loginMember";
    }

    //@RequestMapping(value="/members/login", method = {RequestMethod.POST})
    @PostMapping("/members/login")
    public String login2(@Valid @ModelAttribute LoginForm form, BindingResult b
                , HttpServletResponse response) {

        if(b.hasErrors()) return "members/loginMember";

        Member member = loginRepository.login(form.getMemberId(), form.getPw());
        // 여기서부터 야무지게 안들어간다.. 왜 member가 안들어갈까
        // 매핑관계 문제인지 값이 전달이 제대로 안되는지 봐야할 거 같다

        if(member == null) {
            b.reject("loginFail", "아이디 혹은 비밀번호가 틀림!");
            return "members/loginMember";
        }

        System.out.println("멤버이름 = " + member.getName());

        String a = null;

        if(member.getStatus() == MemberStatus.member) {
            a = "mainPage2";
        }

        if(member.getStatus() == MemberStatus.admin) {
            a = "mainPage3";
        }

        sessionManager.createSession(member, response);
        session = request.getSession();
        session.setAttribute("member", member);

        return a;
    }

    @PostMapping("/members/logout")
    public String logout() {
        session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "mainPage1";
    }
}
