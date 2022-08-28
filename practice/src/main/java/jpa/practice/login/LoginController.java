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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@Transactional
public class LoginController {

    private final LoginRepository loginRepository;
    private final SessionManager sessionManager;

    private final EntityManager em;

    @GetMapping("/members/login")
    public String login(@ModelAttribute("loginForm") LoginForm form) {
        return "members/loginMember";
    }

    @PostMapping("/members/login")
    public String login2(@Valid @ModelAttribute LoginForm form, BindingResult b
                , HttpServletResponse response, Model model) {

        if(b.hasErrors()) return "members/loginMember";

        Member logMember = loginRepository.login(form.getMemberId(), form.getPw());

        if(logMember == null) {
            b.reject("loginFail", "아이디 혹은 비밀번호가 틀림!");
            return "members/loginMember";
        }

        String a = null;

        if(logMember.getStatus() == MemberStatus.member) {
            a = "mainPage2";
        }

        if(logMember.getStatus() == MemberStatus.admin) {
            a = "mainPage3";
        }

        model.addAttribute("member", logMember);

        sessionManager.createSession(logMember, response);

        return a;
    }

    @PostMapping("/members/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "mainPage1";
    }
}
