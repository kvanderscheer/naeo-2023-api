package us.vanderscheer.naeo2023.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import us.vanderscheer.naeo2023.domain.dto.CommentDTO;
import us.vanderscheer.naeo2023.domain.entity.Comment;
import us.vanderscheer.naeo2023.domain.json.CommentAddRequest;
import us.vanderscheer.naeo2023.domain.json.CommentUpdateRequest;
import us.vanderscheer.naeo2023.exception.RequestValidationException;
import us.vanderscheer.naeo2023.exception.ResourceNotFoundException;
import us.vanderscheer.naeo2023.repositories.CalendarDAO;
import us.vanderscheer.naeo2023.repositories.CommentDAO;
import us.vanderscheer.naeo2023.repositories.EventRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Qualifier("jpaComment")
    private final CommentDAO commentsDAO;
    @Qualifier("jpaCalendar")
    private final CalendarDAO calendarDAO;
    private final EventRepository eventRepository;
    private final CommentDTOMapper commentDTOMapper;

    public List<CommentDTO> getComments() {
        return commentsDAO.selectAllComments()
                .stream()
                .map(commentDTOMapper)
                .sorted(Comparator.comparing(CommentDTO::created))
                .collect(Collectors.toList());
    }

    public List<CommentDTO> getCommentsByEntity(UUID entityId) {
        List<Comment> comments = commentsDAO.selectCommentsByEntity(entityId);
        return comments
                .stream()
                .map(commentDTOMapper)
                .sorted(Comparator.comparing(CommentDTO::created))
                .collect(Collectors.toList());
    }

    public CommentDTO getCommentById(UUID commentId) {
        return commentsDAO.selectCommentById(commentId)
                .map(commentDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("comment with id [%s] not found".formatted(commentId)));
    }

    public CommentDTO add(CommentAddRequest commentAddRequest) {

        switch (commentAddRequest.entityType()) {
            case calendar -> calendarDAO.selectCalendarById(commentAddRequest.entityId()).orElseThrow(
                    () -> new ResourceNotFoundException("calendar with [%s] not found".formatted(commentAddRequest.entityId()))
            );
            case event -> eventRepository.findById(commentAddRequest.entityId()).orElseThrow(
                    () -> new ResourceNotFoundException("event with [%s] not found".formatted(commentAddRequest.entityId()))
            );
            case speaker -> throw new RequestValidationException("unable to added comment due to invalid object type [%s]".formatted(commentAddRequest.entityType()));
        }

        Comment comment = new Comment(
                commentAddRequest.entityType(),
                commentAddRequest.entityId(),
                commentAddRequest.authorEmail(),
                commentAddRequest.authorName(),
                commentAddRequest.content()
        );
        return commentDTOMapper.apply(commentsDAO.save(comment));
    }

    public CommentDTO update(UUID commentId, CommentUpdateRequest updateRequest) {

        // TODO: for JPA use .getReferenceById(customerId) as it does does not bring object into memory and instead a reference
        Comment comment = commentsDAO.selectCommentById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "comment with id [%s] not found".formatted(commentId)
                ));

        boolean changes = false;
        if (updateRequest.authorEmail() != null && !updateRequest.authorEmail().equals(comment.getAuthorEmail())) {
            comment.setAuthorEmail(updateRequest.authorEmail());
            changes = true;
        }

        if (updateRequest.authorName() != null && !updateRequest.authorName().equals(comment.getAuthorName())) {
            comment.setAuthorName(updateRequest.authorName());
            changes = true;
        }

        if (updateRequest.content() != null && !updateRequest.content().equals(comment.getContent())) {
            comment.setContent(updateRequest.content());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("No changes found");
        }

        return commentDTOMapper.apply(commentsDAO.save(comment));
    }

    public void delete(UUID commentId) {
        if (!commentsDAO.existsById(commentId)) {
            throw new ResourceNotFoundException(
                    "comment with id [%s] not found".formatted(commentId)
            );
        }
        commentsDAO.deleteById(commentId);
    }
}
