package us.vanderscheer.naeo2023.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import us.vanderscheer.naeo2023.domain.dto.CalendarDTO;
import us.vanderscheer.naeo2023.exception.ResourceNotFoundException;
import us.vanderscheer.naeo2023.repositories.CalendarDAO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {

    @Qualifier("jpaCalendar")
    private final CalendarDAO calendarDAO;
    private final CalendarDTOMapper calendarDTOMapper;

    public List<CalendarDTO> getCalendars() {
        return calendarDAO.selectAllCalendars()
                .stream()
                .map(calendarDTOMapper)
                .collect(Collectors.toList());
    }

    public CalendarDTO getCalendar(UUID calendarId) {
        return calendarDAO.selectCalendarById(calendarId)
                .map(calendarDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "calendar with id [%s] not found".formatted(calendarId)
                ));
    }
}
