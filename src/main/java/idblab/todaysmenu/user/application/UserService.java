package idblab.todaysmenu.user.application;


import idblab.todaysmenu.user.domain.Gender;
import idblab.todaysmenu.user.domain.UserExercise;
import idblab.todaysmenu.user.dto.command.SignupRequestDto;
import idblab.todaysmenu.user.domain.User;
import idblab.todaysmenu.user.repository.UserExerciseRepository;
import idblab.todaysmenu.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserExerciseRepository userExerciseRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(SignupRequestDto signupRequest) {
        if (!signupRequest.getPassword().equals(signupRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        UserExercise mock = UserExercise.builder()
                .wk_hg_act(signupRequest.getHighIntensityActivity().getTimesPerWeek())
                .dy_hg_act_hr(signupRequest.getHighIntensityActivity().getHoursPerDay())
                .dy_hg_act_mn(signupRequest.getHighIntensityActivity().getMinutesPerDay())
                .wk_md_act(signupRequest.getModerateIntensityActivity().getTimesPerWeek())
                .dy_md_act_hr(signupRequest.getModerateIntensityActivity().getHoursPerDay())
                .dy_md_act_mn(signupRequest.getModerateIntensityActivity().getMinutesPerDay())
                .build();

        double bmi = calcBmi(signupRequest.getWeight(), signupRequest.getHeight());
        String bodyActivity = determineBodyActivity(mock);
        double avgCal = calcAvgCal(bmi, bodyActivity, signupRequest.getWeight());
        double mealCal = calcMealCal(avgCal);

        User user = User.builder()
                .nickname(signupRequest.getNickname())
                .account(signupRequest.getAccount())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .email(signupRequest.getEmail())
                .gender(Gender.valueOf(signupRequest.getGender().name()))
                .birth(signupRequest.getBirth().atStartOfDay())
                .weight(signupRequest.getWeight())
                .height(signupRequest.getHeight())
                .totalKcal(avgCal)
                .bmi(bmi)
                .build();

        UserExercise userExerciseDto = UserExercise.builder()
                .wk_hg_act(signupRequest.getHighIntensityActivity().getTimesPerWeek())
                .dy_hg_act_hr(signupRequest.getHighIntensityActivity().getHoursPerDay())
                .dy_hg_act_mn(signupRequest.getHighIntensityActivity().getMinutesPerDay())
                .wk_md_act(signupRequest.getModerateIntensityActivity().getTimesPerWeek())
                .dy_md_act_hr(signupRequest.getModerateIntensityActivity().getHoursPerDay())
                .dy_md_act_mn(signupRequest.getModerateIntensityActivity().getMinutesPerDay())
                .user(user)
                .build();

        UserExercise userExercise = userExerciseRepository.save(userExerciseDto);

        return userRepository.save(user);
    }

    /**
     * BMI 구하는 공식
     * @param weight
     * @param height
     * @return
     */
    private double calcBmi(double weight, double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero");
        }
        double bmi = weight / (height * height)*10000;
        BigDecimal bd = new BigDecimal(bmi).setScale(1, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    /**
     * 활동지수 구하는 공식
     * @param UserExercise
     * @return
     */
    private String determineBodyActivity(UserExercise exercise) {
        if (exercise.getWk_hg_act() >= 3 || exercise.getDy_hg_act_hr() >= 20 || exercise.getDy_hg_act_mn() >= 30) {
            return "10"; // 고강도 활동
        } else if (exercise.getWk_md_act() >= 3 || exercise.getDy_md_act_hr() >= 20 || exercise.getDy_md_act_mn() >= 30) {
            return "20"; // 중강도 활동
        } else if (exercise.getWk_walk() >= 5 || exercise.getDy_walk_hr() >= 20 || exercise.getDy_walk_mn() >= 30) {
            return "30"; // 걷기 활동
        } else {
            return "0"; // 활동 없음
        }
    }

    /**
     * 평균체중 구하는 공식
     * @param gender
     * @param height
     * @return
     */
    private double calcAvgKg(Gender gender, double height) {
        double heightMeters = height * 0.01;
        if (gender == Gender.MALE) {
            return (heightMeters * heightMeters) * 22;
        } else if (gender == Gender.FEMALE) {
            return (heightMeters * heightMeters) * 21;
        } else {
            throw new IllegalArgumentException("Invalid gender value");
        }
    }

    /**
     * 평균 섭취량 구하는 공식
     * @param bmi
     * @param bodyActy
     * @param weight
     * @return
     */
    private double calcAvgCal(double bmi, String bodyActy, double weight) {
        int calMultiplier;
        switch (bodyActy) {
            case "10":
                calMultiplier = (bmi >= 25.0) ? 35 : (bmi >= 18.5) ? 40 : 45;
                break;
            case "20":
                calMultiplier = (bmi >= 25.0) ? 30 : (bmi >= 18.5) ? 35 : 40;
                break;
            case "30":
                calMultiplier = (bmi >= 25.0) ? 25 : (bmi >= 18.5) ? 30 : 35;
                break;
            default:
                throw new IllegalArgumentException("Invalid body activity value");
        }
        return calMultiplier * weight;
    }

    /**
     * 1끼당 섭취 칼로리 구하는 공식
     * @param avgCal
     * @return
     */
    private double calcMealCal(double avgCal) {
        return Math.round((avgCal / 3) * 100.0) / 100.0;
    }
}