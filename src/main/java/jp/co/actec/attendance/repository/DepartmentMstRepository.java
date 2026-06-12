package jp.co.actec.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.actec.attendance.model.DepartmentMst;

@Repository
public interface DepartmentMstRepository extends JpaRepository<DepartmentMst, String> {}
