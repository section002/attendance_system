package jp.co.actec.attendance.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.actec.attendance.model.EmployeeMst;

public interface EmployeeMstRepository extends JpaRepository<EmployeeMst, String> {

    List<EmployeeMst> findByMailAddress(String mailAddress);
    
}
