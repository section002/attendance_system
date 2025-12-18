package jp.co.actec.attendance.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.co.actec.attendance.model.RouteMst;
import jp.co.actec.attendance.repository.RouteRepository;

@Service
public class RouteService {
    @Autowired
    RouteRepository routeRepository;

    public List<RouteMst> findAllRoutes() {
        return routeRepository.findAll();
    }
}
