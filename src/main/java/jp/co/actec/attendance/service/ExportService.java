package jp.co.actec.attendance.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import jp.co.actec.attendance.constant.LateReason;
import jp.co.actec.attendance.model.Attendance;

@Service
public class ExportService {

    public void exportLateAttendancesCsv(List<Attendance> attendances, HttpServletResponse response) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String filename = "遅刻者情報_" + timestamp + ".csv";

        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8)
        );

        writer.write("\uFEFF");
        writer.write("氏名,路線,日付,出社時刻,遅刻分数,理由\n");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        for (Attendance attendance : attendances) {
            String name = attendance.getEmployee().getEmpLname() + attendance.getEmployee().getEmpFname();
            String route = attendance.getRoute().getRouteName();
            String dateStr = attendance.getDate().format(dateFormatter);
            LocalTime attendanceTime = LocalTime.of(9, 0).plusMinutes(attendance.getLateTime());
            String timeStr = attendanceTime.format(timeFormatter);
            String lateMinutes = attendance.getLateTime() + "分";
            String reason = getLateReasonLabel(attendance.getLateReason());

            writer.write(String.format("%s,%s,%s,%s,%s,%s%n", name, route, dateStr, timeStr, lateMinutes, reason));
        }

        writer.flush();
        writer.close();
    }

    private String getLateReasonLabel(int code) {
        for (LateReason reason : LateReason.values()) {
            if (reason.getCode() == code) {
                return reason.getLabel();
            }
        }
        return "不明";
    }
}