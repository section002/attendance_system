package jp.co.actec.attendance.model;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePasswordId implements Serializable {
    private String empId;
    private String password;
}