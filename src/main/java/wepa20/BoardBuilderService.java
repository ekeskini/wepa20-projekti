/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wepa20.Account.Account;
import wepa20.Account.AccountRepository;
import wepa20.Comment.Comment;
import wepa20.Comment.CommentRepository;
import wepa20.Post.Post;
import wepa20.Post.PostRepository;
import wepa20.Post.PostService;

/**
 *
 * @author Elias Keski-Nisula
 */
@Transactional
@Service
public class BoardBuilderService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentRepository commentRepository;
    
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }   
    public Page<Post> getPostsByPage(Integer page) {
        return postService.getPostsByPage(page);
    }
    public void saveNewPost(Post post) {
	postService.saveNewPost(post);
    }
    
    public void addPostToAccount(String username, Post post) {
        getAccountByUsername(username).getPosts().add(post);
    }
    public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.findCommentByParent(post);
    }
    public Post getPostById(Long id) {
        return postService.getPostById(id);
    }
    
    public void saveNewComment(Comment comment) {
        commentRepository.save(comment);
    }
    
    public void addCommentToAccount(String username, Comment comment) {
        getAccountByUsername(username).getComments().add(comment);
    }
    
    public void likePostById(Long postid) {
        Account acc = accountRepository
                .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Post p = postService.getPostById(postid);
        List<Account> postLikers = p.getLikers();
        
        if (postLikers.contains(acc)) {
            postLikers.remove(acc);
            p.setLikes(p.getLikes() - 1);
            return;
        }
        postLikers.add(acc);
        p.setLikes(p.getLikes() + 1);
    }
    
    public void likeCommentById(Long commentid) {
        Account acc = accountRepository
                .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Comment c = commentRepository.getOne(commentid);
        List<Account> commentLikers = c.getLikers();
        if (commentLikers.contains(acc)) {
            commentLikers.remove(acc);
            c.setLikes(c.getLikes() - 1);
            return;
        }
        commentLikers.add(acc);
        c.setLikes(c.getLikes() + 1);
    }
}
