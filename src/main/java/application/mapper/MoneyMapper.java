package application.mapper;

import domain.model.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.Date;

@Mapper(
        componentModel = "jakarta",
        uses = {DateMapper.class}
)
public interface MoneyMapper {

    // ---- Domain -> API ----
    @Mapping(source = "id", target = "moneyId")
    @Mapping(source = "balance", target = "balance")
    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "currency.code", target = "currencyCode")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "toDate")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "toDate")
    api.model.Money toApi(domain.model.Money entity);

    // ---- API -> Domain ----
    @Mapping(source = "moneyId", target = "id")
    @Mapping(source = "balance", target = "balance")
    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "currencyCode", target = "currency")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "toLocalDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "toLocalDateTime")
    domain.model.Money toDomain(api.model.Money dto);


    // ---- Helper para mapear currencyCode -> Currency entity ----
    default Currency map(String currencyCode) {
        if (currencyCode == null) return null;
        return Currency.builder().code(currencyCode).build();
    }

    default String map(Currency currency) {
        return currency != null ? currency.getCode() : null;
    }
}
