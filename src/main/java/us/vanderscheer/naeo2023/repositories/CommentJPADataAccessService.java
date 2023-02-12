package us.vanderscheer.naeo2023.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import us.vanderscheer.naeo2023.domain.entity.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository("jpaComment")
public class CommentJPADataAccessService implements CommentDAO {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> selectAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> selectCommentById(UUID commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public boolean existsById(UUID commentId) {
        return commentRepository.existsById(commentId);
    }

    @Override
    public void deleteById(UUID commentId) {
        commentRepository.deleteById(commentId);
    }
}
