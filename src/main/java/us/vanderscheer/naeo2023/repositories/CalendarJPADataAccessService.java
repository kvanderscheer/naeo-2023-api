package us.vanderscheer.naeo2023.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import us.vanderscheer.naeo2023.domain.entity.Calendar;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository("jpaCalendar")
public class CalendarJPADataAccessService implements CalendarDAO {

    private final CalendarRepository calendarRepository;

    @Override
    public List<Calendar> selectAllCalendars() {
        return calendarRepository.findAll();
    }

    @Override
    public Optional<Calendar> selectCalendarById(UUID calendarId) {
        return calendarRepository.findById(calendarId);
    }
}
