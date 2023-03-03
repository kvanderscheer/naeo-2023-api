package us.vanderscheer.naeo2023.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import us.vanderscheer.naeo2023.domain.entity.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByCalendarId(UUID eventId);
    List<Event> findByCalendarIdAndFromDateTimeAfterAndToDateTimeBeforeOrderByFromDateTimeAsc(UUID calendarId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Event> findByCalendarIdAndToDateTimeBeforeOrderByFromDateTimeAsc(UUID calendarId, LocalDateTime startDateTime);

    List<Event> findByCalendarIdAndFromDateTimeAfterOrderByFromDateTimeAsc(UUID calendarId, LocalDateTime endDateTime);
}
