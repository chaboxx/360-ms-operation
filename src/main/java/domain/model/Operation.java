package domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Operations")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Operation extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private Integer operationId;

    @ManyToOne
    @JoinColumn(name = "operation_type")
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "money_master")
    private Money moneyMaster;

    @ManyToOne
    @JoinColumn(name = "money_secondary")
    private Money moneySecondary;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
