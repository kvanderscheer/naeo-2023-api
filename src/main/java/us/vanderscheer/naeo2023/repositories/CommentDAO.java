package us.vanderscheer.naeo2023.repositories;

import us.vanderscheer.naeo2023.domain.entity.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentDAO {
    List<Comment> selectAllComments();
    List<Comment> selectCommentsByEntity(UUID entityId);
    Comment save(Comment comment);
    Optional<Comment> selectCommentById(UUID commentId);
    boolean existsById(UUID commentId);
    void deleteById(UUID commentId);
}
