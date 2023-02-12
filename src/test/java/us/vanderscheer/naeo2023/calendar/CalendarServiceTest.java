package us.vanderscheer.naeo2023.calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import us.vanderscheer.naeo2023.domain.dto.CalendarDTO;
import us.vanderscheer.naeo2023.domain.entity.Calendar;
import us.vanderscheer.naeo2023.exception.ResourceNotFoundException;
import us.vanderscheer.naeo2023.repositories.CalendarDAO;
import us.vanderscheer.naeo2023.services.CalendarDTOMapper;
import us.vanderscheer.naeo2023.services.CalendarService;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalendarServiceTest {

    @Mock
    private CalendarDAO calendarDAO;
    private CalendarService underTest;
    private final CalendarDTOMapper calendarDTOMapper = new CalendarDTOMapper();

    @BeforeEach
    void beforeEach() {
        underTest = new CalendarService(calendarDAO, calendarDTOMapper);
    }

    @Test
    void getAllCalendars() {
        // When
        underTest.getCalendars();

        // Then
        verify(calendarDAO).selectAllCalendars();
    }

    @Test
    void canGetCalendar() {
        // Given
        UUID id = UUID.fromString("1136e90e-dcb5-4169-b651-a8ab8e2cb68e");
        Calendar calendar = new Calendar(
                id,
                "2023 NAEO Annual Conference",
                "The 2023 NAEO Annual Conference agenda.",
                "Sheraton New Orleans Hotel");
        when(calendarDAO.selectCalendarById(id)).thenReturn(Optional.of(calendar));

        CalendarDTO expected = calendarDTOMapper.apply(calendar);

        // When
        CalendarDTO actual = underTest.getCalendar(id);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void willThrowWhenGetCalendarReturnEmptyOptional() {
        // Given
        UUID id = UUID.fromString("1136e90e-dcb5-4169-b651-a8ab8e2cb68e");

        when(calendarDAO.selectCalendarById(id)).thenReturn(Optional.empty());

        // When
        // Then
        assertThatThrownBy(() -> underTest.getCalendar(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("calendar with id [%s] not found".formatted(id));
    }

}
