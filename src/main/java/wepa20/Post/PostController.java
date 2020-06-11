package wepa20.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
	@Autowired
	private PostService postservice;
	
	@GetMapping("/home")
	public String viewHome(Model model) {
		
		model.addAttribute("posts", postservice.getPostsByPage(0));		
		return "home";
	}
	
	@PostMapping("/home")
	public String addPost(@RequestParam String content) {
		Post post = new Post(LocalDateTime.now(), content);
		postservice.saveNewPost(post);
		
		return "redirect:/home";
	}
}
