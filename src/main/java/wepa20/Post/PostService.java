package wepa20.Post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import wepa20.Account.Account;
import wepa20.Account.AccountRepository;
import wepa20.Comment.CommentRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postrepository;
	
	public Page<Post> getPostsByPage(Integer pagenumber) {
		Pageable p = PageRequest.of((0 + pagenumber), 25, Sort.by("timestamp").descending());
		return postrepository.findAll(p);		
	}
	
	public void saveNewPost(Post post) {
		postrepository.save(post);
	}
	
}
