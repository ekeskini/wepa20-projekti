/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wepa20.entities.Account;
import wepa20.entities.Comment;
import wepa20.services.BoardBuilderService;
import wepa20.entities.Post;

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
        
        Comment comment = new Comment(acc, LocalDateTime.now(), commentcontent, parent, 0, new ArrayList<>());
        boardBuilder.saveNewComment(comment);
        boardBuilder.addCommentToAccount(username, comment);
        
        return "redirect:/home";
    }
    
    @PostMapping("/home/likecomment/{commentid}")
    public String likeComment(@PathVariable Long commentid) {
        boardBuilder.likeCommentById(commentid);
        return "redirect:/home";
    }
}
