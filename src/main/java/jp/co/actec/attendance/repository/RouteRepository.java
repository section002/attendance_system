package jp.co.actec.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jp.co.actec.attendance.model.RouteMst;

public interface RouteRepository extends JpaRepository<RouteMst, Integer> {}
