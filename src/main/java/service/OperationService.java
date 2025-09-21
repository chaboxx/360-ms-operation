package service;

import domain.command.CreateOperationCommand;
import domain.model.Operation;
import domain.repository.IOperationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class OperationService {

    private final IOperationRepository operationRepository;

    @Transactional
    public Operation createOperation(CreateOperationCommand command) {
        Operation operation = Operation.builder()
                .operationName(command.getOperationName())
                .operationType(command.getOperationType())
                .build();

        return operationRepository.create(operation);
    }
}
