package idblab.todaysmenu.allergy.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "AllergySet")
public class AllergySet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allergySetId;

    @Column(unique = true, nullable = false, length = 30)
    private String allergySetName;

    @Column(unique = true, nullable = false, length = 30)
    @OneToMany(mappedBy = "allergySet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Allergy> allergies = new ArrayList<>();
}
