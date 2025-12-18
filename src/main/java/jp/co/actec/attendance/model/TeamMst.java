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
@Table(name = "TEAM_MST")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamMst implements Serializable {
    @Id
    @Column(name = "TEAM_ID", length = 5, nullable = false)
    private String teamId;

    @Column(name = "TEAM_NAME", length = 50, nullable = false)
    private String teamName;

    @Column(name = "UNIT_NO", length = 1, nullable = false)
    private String unitNo;
}