/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.Comment;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wepa20.Account.Account;
import wepa20.BoardBuilderService;
import wepa20.Post.Post;

/**
 *
 * @author Elias Keski-Nisula
 */
@Controller
public class CommentController {
    @Autowired
    private BoardBuilderService boardBuilder;
    
    @PostMapping("/home/{postid}/comment")
    public String addComment(@PathVariable Long postid, @RequestParam String commentcontent) {
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account acc = boardBuilder.getAccountByUsername(username);
        Post parent = (boardBuilder.getPostById(postid));
        
        Comment comment = new Comment(acc, LocalDateTime.now(), commentcontent, parent);
        boardBuilder.saveNewComment(comment);
        boardBuilder.addCommentToAccount(username, comment);
        
        return "redirect:/home";
    }
}
