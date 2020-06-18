package wepa20.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wepa20.entities.Account;
import wepa20.services.BoardBuilderService;
import wepa20.entities.Post;

@Controller
public class HomeController {
	@Autowired
        public BoardBuilderService boardBuilder;
	
	@GetMapping("/home")
	public String viewHome(Model model) {		
            String username = SecurityContextHolder
                    .getContext().getAuthentication().getName();
            model.addAttribute("posts", boardBuilder.getPostsByPage(0)); 
            model.addAttribute("currentuser", username);
            
            return "home";
	}
	@Transactional
	@PostMapping("/home")
	public String addPost(@RequestParam String content) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Account acc = boardBuilder.getAccountByUsername(username);
            Post post = new Post(acc, LocalDateTime.now(), content, new ArrayList<>(), 0, new ArrayList<>());
            boardBuilder.saveNewPost(post);
            return "redirect:/home";
	}
        
        @PostMapping("/home/likepost/{postid}")
        public String likePost(@PathVariable Long postid) {
            boardBuilder.likePostById(postid);
            return "redirect:/home";
        }
}
