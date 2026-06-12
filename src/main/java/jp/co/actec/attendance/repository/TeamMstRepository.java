package jp.co.actec.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.actec.attendance.model.TeamMst;

@Repository
public interface TeamMstRepository extends JpaRepository<TeamMst, String> {}
