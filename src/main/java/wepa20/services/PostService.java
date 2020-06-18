package wepa20.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import wepa20.entities.Post;
import wepa20.repositories.PostRepository;

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
        
        public Post getPostById(Long id) {
            return postrepository.getOne(id);
        }
	
}
