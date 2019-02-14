package hhu.propra2.illegalskillsexception.frently.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainView(){
        return "redirect:/index.html";
    }
}
