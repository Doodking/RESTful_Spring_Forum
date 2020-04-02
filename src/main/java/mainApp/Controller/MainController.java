package mainApp.Controller;

import mainApp.Model.User;
import mainApp.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private PostRepo postRepo;

    @GetMapping
    public String get(Model model, @AuthenticationPrincipal User user){
        HashMap<Object, Object> data = new HashMap<>();
        if(user != null) {
            data.put("user", user);
            data.put("posts", postRepo.findAll());
        }
        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("frontEndData", data);
        return "index";
    }
}
