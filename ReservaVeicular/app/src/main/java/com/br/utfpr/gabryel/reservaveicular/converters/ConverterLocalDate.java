package com.br.utfpr.gabryel.reservaveicular.converters;

import androidx.room.TypeConverter;

import java.time.LocalDate;

public class ConverterLocalDate {

    @TypeConverter
    public static LocalDate longParaLocalDate(Long value) {
        return value == null ? null : LocalDate.ofEpochDay(value);
    }

    @TypeConverter
    public static Long localDateToLong(LocalDate date) {
        return date == null ? null :  date.toEpochDay();
    }
}
