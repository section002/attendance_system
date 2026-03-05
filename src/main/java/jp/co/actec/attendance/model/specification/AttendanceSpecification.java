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
        LocalDate to
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (from != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("date"), from));
            }

            if (to != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("date"), to));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}