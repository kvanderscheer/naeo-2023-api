package us.vanderscheer.naeo2023.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.vanderscheer.naeo2023.domain.dto.SpeakerDTO;
import us.vanderscheer.naeo2023.exception.ResourceNotFoundException;
import us.vanderscheer.naeo2023.repositories.SpeakerRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SpeakerService {

    private final SpeakerRepository speakerRepository;
    private final SpeakerDTOMapper speakerDTOMapper;

    public SpeakerDTO getSpeaker(UUID speakerId) {
        return speakerRepository.findById(speakerId)
                .map(speakerDTOMapper)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "speaker with id [%s] not found".formatted(speakerId)
                        ));
    }

    public List<SpeakerDTO> getSpeakers() {
        return speakerRepository.findAll()
                .stream()
                .map(speakerDTOMapper)
                .sorted(Comparator.comparing(SpeakerDTO::lastName))
                .collect(Collectors.toList());
    }
}
