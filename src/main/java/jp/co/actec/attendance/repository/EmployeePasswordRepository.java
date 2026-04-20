package jp.co.actec.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.actec.attendance.model.EmployeePassword;
import jp.co.actec.attendance.model.EmployeePasswordId;

public interface EmployeePasswordRepository extends JpaRepository<EmployeePassword, EmployeePasswordId> {
}