package jp.co.actec.attendance.controller;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBTestController {
    private final DataSource dataSource;

    public DBTestController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/db-test")
    public String testConnection() {
        try (Connection conn = dataSource.getConnection()) {
            return "DB connection OK";
        } catch (Exception e) {
            return "DB connection NG: " + e.getMessage();
        }
    }
}
