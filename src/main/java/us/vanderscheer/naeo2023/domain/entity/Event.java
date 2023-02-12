package us.vanderscheer.naeo2023.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "event_id")
    private UUID eventId;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    @NotNull
    private Calendar calendar;

    @Column(name = "from_datetime")
    private LocalDateTime fromDateTime;

    @Column(name = "to_datetime")
    private LocalDateTime toDateTime;

    @Size(min = 0, max = 100)
    private String name;

    @Size(min = 0, max = 1000)
    @Column(length=1000)
    private String description;

    @NotBlank
    private String location;

    @JoinTable(
            name = "events_speakers",
            joinColumns = @JoinColumn(
                    name = "event_id",
                    referencedColumnName = "event_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "speaker_id",
                    referencedColumnName = "speaker_id"
            )
    )
    @ManyToMany
    private Set<Speaker> speakers = new HashSet<>();
}
