package us.vanderscheer.naeo2023.services;

import org.springframework.stereotype.Service;
import us.vanderscheer.naeo2023.domain.dto.CommentDTO;
import us.vanderscheer.naeo2023.domain.entity.Comment;

import java.util.function.Function;

@Service
public class CommentDTOMapper implements Function<Comment, CommentDTO> {
    @Override
    public CommentDTO apply(Comment comment) {
        return new CommentDTO(
                comment.getCommentId(),
                comment.getVersion(),
                comment.getEntityType(),
                comment.getEntityId(),
                comment.getAuthorEmail(),
                comment.getAuthorName(),
                comment.getContent(),
                comment.getCreated(),
                comment.getUpdated()
        );
    }
}
