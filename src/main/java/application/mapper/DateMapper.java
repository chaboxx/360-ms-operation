package application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.Date;

@Mapper(componentModel = "jakarta")
public interface DateMapper {

    @Named("toDate")
    default Date toDate(LocalDateTime ldt) {
        return ldt != null ? java.sql.Timestamp.valueOf(ldt) : null;
    }

    @Named("toLocalDateTime")
    default LocalDateTime toLocalDateTime(Date date) {
        return date != null ? new java.sql.Timestamp(date.getTime()).toLocalDateTime() : null;
    }
}