package br.com.bank.configuration.converter

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDateTimeAttributeConverter : AttributeConverter<LocalDateTime, Timestamp> {

    override fun convertToDatabaseColumn(localDateTime: LocalDateTime): Timestamp? =
            if (localDateTime != null) Timestamp.valueOf(localDateTime) else null

    override fun convertToEntityAttribute(timestamp: Timestamp?): LocalDateTime? = timestamp?.toLocalDateTime()
}