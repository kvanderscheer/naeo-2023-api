package us.vanderscheer.naeo2023.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import us.vanderscheer.naeo2023.domain.entity.Calendar;

import java.util.UUID;

public interface CalendarRepository extends JpaRepository<Calendar, UUID> {
}
