package br.com.bank.bank.configuration.converter

import java.sql.Date
import java.time.LocalDate
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDateAttributeConverter: AttributeConverter<LocalDate, Date> {

    override fun convertToDatabaseColumn(localDate: LocalDate?): Date? =
            if (localDate != null) Date.valueOf(localDate) else null

    override fun convertToEntityAttribute(date: Date?): LocalDate? = date?.toLocalDate();
}