package jpa.practice.login;

import jpa.practice.SessionManager;
import jpa.practice.member.Member;
import jpa.practice.member.MemberSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginRepository loginRepository;
    private final SessionManager sessionManager;

    @GetMapping("/members/login")
    public String login(@ModelAttribute("loginForm") LoginForm form) {
        return "members/loginMember";
    }

    @PostMapping("/members/login")
    public String login2(@Valid @ModelAttribute  LoginForm form, BindingResult b
                , HttpServletResponse response) {

        if(b.hasErrors()) return "members/loginMember";

        Member logMember = loginRepository.login(form.getMemberId(), form.getPw());

        if(logMember == null) {
            b.reject("loginFail", "아이디 혹은 비밀번호가 틀림!");
            return "members/loginMember";
        }

        sessionManager.createSession(logMember, response);

        return "redirect:/";
    }

    @PostMapping("/members/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
