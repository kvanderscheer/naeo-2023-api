package us.vanderscheer.naeo2023.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import us.vanderscheer.naeo2023.domain.entity.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByEntityId(UUID entityId);
}
