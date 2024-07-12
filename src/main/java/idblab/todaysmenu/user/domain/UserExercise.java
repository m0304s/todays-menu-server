package idblab.todaysmenu.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "UserExercise")
public class UserExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userExerciseId", updatable = false, nullable = false)
    private Long userExerciseId;

    @OneToOne
    @JoinColumn(name = "userid")
    private User user;

    @Column(nullable = false)
    private int wk_hg_act;      //주간_고강도 신체 활동 날짜

    @Column(nullable = false)
    private int dy_hg_act_hr;   //하루_고강도 신체 활동 시간_시간

    @Column(nullable = false)
    private int dy_hg_act_mn;   //하루 고강도 신체 활동 시간_분

    @Column(nullable = false)
    private int wk_md_act;      //주간_중강도 신체 활동 날짜

    @Column(nullable = false)
    private int dy_md_act_hr;   //하루_중강도 신체 활동 시간_시간

    @Column(nullable = false)
    private int dy_md_act_mn;   //하루 중강도 신체 활동 시간_분

    @Column(nullable = false)
    private int wk_walk;    //일주일에 10분이상 걸은 날짜

    @Column(nullable = false)
    private int dy_walk_hr;    //하루동안 걷는 평균 시간_시간

    @Column(nullable = false)
    private int dy_walk_mn;  //하루동안 걷는 평균 시간_분
}
