package wepa20.Comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wepa20.Post.Post;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findCommentByParent(Post parent);
}
