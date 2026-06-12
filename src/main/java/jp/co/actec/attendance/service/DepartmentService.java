package jp.co.actec.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.actec.attendance.model.DepartmentMst;
import jp.co.actec.attendance.repository.DepartmentMstRepository;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMstRepository departmentMstRepository;

    public List<DepartmentMst> findAllDepartments() {
        return departmentMstRepository.findAll();
    }
}
