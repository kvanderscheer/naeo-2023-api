package us.vanderscheer.naeo2023.services;

import org.springframework.stereotype.Service;
import us.vanderscheer.naeo2023.domain.dto.CalendarDTO;
import us.vanderscheer.naeo2023.domain.entity.Calendar;

import java.util.function.Function;

@Service
public class CalendarDTOMapper implements Function<Calendar, CalendarDTO> {
    @Override
    public CalendarDTO apply(Calendar calendar) {
        return new CalendarDTO(
                calendar.getId(),
                calendar.getName(),
                calendar.getDescription(),
                calendar.getLocation());
    }
}
