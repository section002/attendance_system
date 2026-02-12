package jp.co.actec.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.actec.attendance.model.EmployeeMst;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeMst, Integer> {}
