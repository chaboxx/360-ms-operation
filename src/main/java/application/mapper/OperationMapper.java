package application.mapper;

import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.Date;

@Mapper(
        componentModel = "jakarta",
        uses = {OperationTypeMapper.class, MoneyMapper.class, DateMapper.class}
)
public interface OperationMapper {

    // ---- Domain -> API ----
    @Mappings({
            @Mapping(source = "operationId", target = "operationId"),
            @Mapping(source = "operationType", target = "operationType"),
            @Mapping(source = "moneyMaster", target = "moneyMaster"),
            @Mapping(source = "moneySecondary", target = "moneySecondary"),
            @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "toDate"),
            @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "toDate")
    })
    api.model.Operation toApi(domain.model.Operation entity);

    // ---- API -> Domain ----
    @Mappings({
            @Mapping(source = "operationId", target = "operationId"),
            @Mapping(source = "operationType", target = "operationType"),
            @Mapping(source = "moneyMaster", target = "moneyMaster"),
            @Mapping(source = "moneySecondary", target = "moneySecondary"),
            @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "toLocalDateTime"),
            @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "toLocalDateTime")
    })
    domain.model.Operation toDomain(api.model.Operation dto);

}
