package us.vanderscheer.naeo2023.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "speakers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "speaker_email_unique",
                        columnNames = "email"
                )
        })
public class Speaker {

    @Id
    @Column(name = "speaker_id")
    private UUID speakerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String company;

    @Column(columnDefinition="TEXT")
    private String bio;
}
