package us.vanderscheer.naeo2023.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.vanderscheer.naeo2023.domain.dto.EventDTO;
import us.vanderscheer.naeo2023.exception.ResourceNotFoundException;
import us.vanderscheer.naeo2023.repositories.EventRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventDTOMapper eventDTOMapper;

    public List<EventDTO> getEvents(UUID calendarId) {
        return eventRepository.findByCalendarIdOrderByFromDateTimeAsc(calendarId)
                .stream()
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
