package wepa20.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa20.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    @EntityGraph(attributePaths = {"comments"})
    @Override
    List<Post> findAll();
}
