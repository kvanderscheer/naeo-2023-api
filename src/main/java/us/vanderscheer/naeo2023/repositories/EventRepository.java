package us.vanderscheer.naeo2023.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import us.vanderscheer.naeo2023.domain.entity.Event;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    @Query("SELECT e FROM Event e WHERE e.calendar.id=?1")
    List<Event> findByCalendarIdOrderByFromDateTimeAsc(UUID eventId);
}
