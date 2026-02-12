package jp.co.actec.attendance.form;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jp.co.actec.attendance.model.Attendance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceForm {
    @NotNull(message = "路線を選択してください")
    private Integer routeId;

    @NotNull(message = "遅刻理由を選択してください")
    private Integer lateReason;

    @NotNull(message = "遅刻時間を入力してください")
    @Min(value = 1, message = "1分以上を入力してください")
    private Integer lateTime;

    @NotNull(message = "電車遅延時間を入力してください")
    @Min(value = 1, message = "1分以上を入力してください")
    private Integer trainDelayTime;

    /**
     * 画面入力値を {@link Attendance} エンティティへ変換します。
     *
     * @return 入力値を反映した {@link Attendance} エンティティ
    */
    public Attendance toEntity() {
        Attendance attendance = new Attendance();

        attendance.setLateTime(lateTime);
        attendance.setTrainDelayTime(trainDelayTime);
        attendance.setDate(LocalDate.now());
        attendance.setCreatedAt(LocalDateTime.now());
        attendance.setUpdatedAt(LocalDateTime.now());

        return attendance;
    }
}
