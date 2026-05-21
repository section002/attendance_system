package jp.co.actec.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.actec.attendance.model.TeamMst;

public interface TeamRepository extends JpaRepository<TeamMst, String> {
    
}
