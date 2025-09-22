package application.repository;

import domain.model.Operation;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


import java.util.UUID;

@ApplicationScoped
public class OperationRepository implements PanacheRepositoryBase<Operation, UUID> {

    public Operation create(Operation operation) {
        persist(operation);
        return operation;
    }
}
