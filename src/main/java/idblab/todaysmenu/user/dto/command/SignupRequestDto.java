package idblab.todaysmenu.user.dto.command;

import idblab.todaysmenu.user.domain.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SignupRequestDto {
    private String nickname;
    private String account;
    private String password;
    private String confirmPassword;
    private String email;
    private Gender gender;
    private LocalDate birth;
    private Double weight;
    private Double height;
    private List<String> allergies;
    private PhysicalActivity highIntensityActivity;
    private PhysicalActivity moderateIntensityActivity;
    private int daysWalkedPerWeek;
    private int averageWalkingTimePerDay;

    @Data
    public static class PhysicalActivity {
        private int timesPerWeek;
        private int hoursPerDay;
        private int minutesPerDay;
    }
}