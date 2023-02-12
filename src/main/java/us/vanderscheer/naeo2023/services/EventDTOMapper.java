package us.vanderscheer.naeo2023.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.vanderscheer.naeo2023.domain.dto.EventDTO;
import us.vanderscheer.naeo2023.domain.entity.Event;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventDTOMapper implements Function<Event, EventDTO> {

    private final SpeakerDTOMapper speakerDTOMapper;

    @Override
    public EventDTO apply(Event event) {
        return new EventDTO(
                event.getEventId(),
                event.getCalendar().getId(),
                event.getFromDateTime(),
                event.getToDateTime(),
                event.getName(),
                event.getDescription(),
                event.getLocation(),
                event.getSpeakers().stream()
                        .map(speakerDTOMapper)
                        .collect(Collectors.toList())
        );
    }
}