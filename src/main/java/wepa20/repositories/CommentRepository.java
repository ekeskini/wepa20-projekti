package wepa20.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wepa20.entities.Comment;
import wepa20.entities.Post;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findCommentByParent(Post parent);
}
