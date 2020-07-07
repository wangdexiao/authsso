package com.sso.authserver.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogInController {

    @GetMapping("/loginpage")
    public String loginpage(Model model){
        model.addAttribute("title", "统一登录中心");
        return "login";
    }
}
