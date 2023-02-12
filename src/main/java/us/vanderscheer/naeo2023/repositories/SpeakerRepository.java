package us.vanderscheer.naeo2023.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import us.vanderscheer.naeo2023.domain.entity.Speaker;

import java.util.UUID;

public interface SpeakerRepository extends JpaRepository<Speaker, UUID> {
}
