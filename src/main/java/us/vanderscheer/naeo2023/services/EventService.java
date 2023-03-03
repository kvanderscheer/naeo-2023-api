package us.vanderscheer.naeo2023.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.vanderscheer.naeo2023.domain.dto.EventDTO;
import us.vanderscheer.naeo2023.domain.entity.Event;
import us.vanderscheer.naeo2023.exception.ResourceNotFoundException;
import us.vanderscheer.naeo2023.repositories.EventRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventDTOMapper eventDTOMapper;

    public List<EventDTO> getEvents(UUID calendarId, LocalDate fromDate, LocalDate toDate) {

        List<Event> events;
        if (Objects.isNull(fromDate) && Objects.isNull(toDate)) {
            events = eventRepository
                    .findByCalendarId(calendarId);
        } else if (Objects.isNull(fromDate)) {
            events = eventRepository
                    .findByCalendarIdAndToDateTimeBeforeOrderByFromDateTimeAsc(calendarId, toDate.atStartOfDay());
        } else if (Objects.isNull(toDate)) {
            events = eventRepository
                    .findByCalendarIdAndFromDateTimeAfterOrderByFromDateTimeAsc(calendarId, fromDate.atStartOfDay());
        } else {
            events = eventRepository
                    .findByCalendarIdAndFromDateTimeAfterAndToDateTimeBeforeOrderByFromDateTimeAsc(calendarId, fromDate.atStartOfDay(), toDate.atStartOfDay());
        }
        return events.stream()
                .map(eventDTOMapper)
                .sorted(Comparator.comparing(EventDTO::fromDateTime))
                .collect(Collectors.toList());
    }

    public EventDTO getEvent(UUID eventId) {
        return eventRepository.findById(eventId)
                .map(eventDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "event with id [%s] not found".formatted(eventId)
                ));
    }
}
