package application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.Date;

@Mapper(
        componentModel = "jakarta",
        uses = {DateMapper.class}
)
public interface OperationTypeMapper {

    // ---- Domain -> API ----
    @Mapping(source = "id", target = "operationId")
    @Mapping(source = "code", target = "operationCode")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "toDate")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "toDate")
    api.model.OperationType toApi(domain.model.OperationType entity);

    // ---- API -> Domain ----
    @Mapping(source = "operationId", target = "id")
    @Mapping(source = "operationCode", target = "code")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "toLocalDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "toLocalDateTime")
    domain.model.OperationType toDomain(api.model.OperationType dto);

}