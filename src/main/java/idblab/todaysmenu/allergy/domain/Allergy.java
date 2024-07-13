package idblab.todaysmenu.allergy.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "Allergy")
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergyId", updatable = false, nullable = false)
    private Long allergyId;

    @Column(name = "allergyName",nullable = false)
    private String allergyName;

    @ManyToOne
    @JoinColumn(name = "allergySetId")
    private AllergySet allergySet;
}
