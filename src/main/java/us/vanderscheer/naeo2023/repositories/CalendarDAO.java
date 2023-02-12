package us.vanderscheer.naeo2023.repositories;

import us.vanderscheer.naeo2023.domain.entity.Calendar;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CalendarDAO {
    List<Calendar> selectAllCalendars();
    Optional<Calendar> selectCalendarById(UUID calendarId);
}
