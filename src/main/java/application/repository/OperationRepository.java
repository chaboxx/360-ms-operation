package application.repository;

import domain.model.Operation;
import domain.repository.IOperationRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class OperationRepository implements IOperationRepository, PanacheRepositoryBase<Operation, UUID> {

    @Override
    public Operation create(Operation operation) {
        persist(operation);
        return operation;
    }
}
