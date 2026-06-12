package jp.co.actec.attendance.model.specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

import jp.co.actec.attendance.model.Attendance;

public class AttendanceSpecification {
    public static Specification<Attendance> search(
            LocalDate from,
            LocalDate to,
            Integer routeId,
            String unitNo,
            String teamId,
            String empNo
        ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (from != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("date"), from));
            }

            if (to != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("date"), to));
            }

            if (routeId != null) {
                predicates.add(cb.equal(root.get("route").get("id"), routeId));
            }

            if (unitNo != null && !unitNo.isBlank()) {
                predicates.add(cb.equal(root.get("employee").get("team").get("unitNo"), unitNo));
            }

            if (teamId != null && !teamId.isBlank()) {
                predicates.add(cb.equal(root.get("employee").get("team").get("teamId"), teamId));
            }

            if (empNo != null && !empNo.isBlank()) {
                predicates.add(cb.equal(root.get("employee").get("empId"), empNo));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}