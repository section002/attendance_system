package jp.co.actec.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.actec.attendance.model.TeamMst;
import jp.co.actec.attendance.repository.TeamMstRepository;

@Service
public class TeamService {
    @Autowired
    TeamMstRepository teamMstRepository;

    public List<TeamMst> findAllTeams() {
        return teamMstRepository.findAll();
    }
}
