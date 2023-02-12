package us.vanderscheer.naeo2023.services;

import org.springframework.stereotype.Service;
import us.vanderscheer.naeo2023.domain.dto.SpeakerDTO;
import us.vanderscheer.naeo2023.domain.entity.Speaker;

import java.util.function.Function;

@Service
public class SpeakerDTOMapper implements Function<Speaker, SpeakerDTO> {
    @Override
    public SpeakerDTO apply(Speaker speaker) {
        return new SpeakerDTO(
                speaker.getSpeakerId(),
                speaker.getFirstName(),
                speaker.getLastName(),
                speaker.getEmail(),
                speaker.getCompany(),
                speaker.getBio()
        );
    }
}
