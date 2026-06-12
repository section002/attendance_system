package jp.co.actec.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.actec.attendance.model.EmployeeMst;
import jp.co.actec.attendance.repository.EmployeeMstRepository;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMstRepository employeeMstRepository;

    public List<EmployeeMst> findAllEmployees() {
        return employeeMstRepository.findAll();
    }
}
