package jp.co.actec.attendance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "ROUTE_MST")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteMst implements Serializable {
    @Id
    @Column(name = "ROUTE_ID", nullable = false)
    private Integer routeId;

    @Column(name = "ROUTE_NAME", length = 255, nullable = false)
    private String routeName;
}