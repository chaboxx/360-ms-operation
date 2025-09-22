package application.controller;

import api.OperationsApi;
import api.model.CreateOperationCommand;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import service.OperationService;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class RestController implements OperationsApi {


    private final OperationService operationService;

    @Override
    public List<api.model.Operation> operationsGet() {
        return List.of();
    }

    @Override
    public api.model.Operation operationsOperationIdGet(Integer operationId) {
        return null;
    }

    @Override
    public api.model.Operation operationsPost(CreateOperationCommand createOperationCommand) {

        return operationService.createOperation(createOperationCommand);
    }
}
