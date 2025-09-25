package service;

import api.model.CreateOperationCommand;
import api.model.CreateOperationResponse;
import application.mapper.OperationMapper;
import application.repository.OperationRepository;
import domain.model.Currency;
import domain.model.Money;
import domain.model.Operation;
import domain.model.OperationType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper mapper;

    @Transactional
    public CreateOperationResponse createOperation(CreateOperationCommand command) {
        // mapear DTO -> entidad
        Operation entity = Operation.builder()
                .build();

        // aquí debes hidratar las relaciones (buscar en repos por id)
        entity.setOperationType(
                OperationType.builder()
                        .code("TX")
                        .build()
        );
        entity.setMoneyMaster(
                Money.builder()
                        .accountId(UUID.randomUUID())
                        .balance(new BigDecimal("2142.12"))
                        .currency(Currency.builder().code("USD").build())
                        .build()
        );
        entity.setMoneySecondary(
                Money.builder()
                        .accountId(UUID.randomUUID())
                        .balance(new BigDecimal("2142132.12"))
                        .currency(Currency.builder().code("USD").build())
                        .build()
        );

        entity.setCreatedAt(LocalDateTime.now());

        Operation saved = operationRepository.create(entity);

        CreateOperationResponse response = new CreateOperationResponse();
        response.setMessage("Github actions v2: Operación creada correctamente");
        response.setOperation(mapper.toApi(saved));
        return response;
    }
}
