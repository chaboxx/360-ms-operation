package application.controller;

import api.OperationsApi;
import api.model.CreateOperationCommand;
import api.model.Money;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
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
//        domain.command.CreateOperationCommand command = domain.command.CreateOperationCommand
//                .builder()
//                .operationName(createOperationCommand.getOperationName())
//                .operationType(createOperationCommand.getOperationType())
//                .build();
//
//        Operation op = operationService.createOperation(command);
        api.model.Operation operation = new api.model.Operation();
        operation.setOperationId(1);
        operation.setMoneyMaster(
                new Money()
        );
        operation.setMoneySecondary(new Money());
        return operation;
    }


}
