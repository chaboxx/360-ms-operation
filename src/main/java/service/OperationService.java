package service;

import api.model.CreateOperationCommand;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class OperationService {

    @Transactional
    public api.model.Operation createOperation(CreateOperationCommand command) {
        api.model.Operation operation = new api.model.Operation();

        operation.setOperationId(1);
        return operation;
    }
}
