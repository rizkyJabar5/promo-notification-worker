package id.co.bni.surrounding.promo.workernotification.config;

import id.co.bni.surrounding.promo.workernotification.additional.entity.enums.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Character> {
    @Override
    public Character convertToDatabaseColumn(Gender gender) {
        if (Objects.isNull(gender)) return null;

        return gender.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(Character s) {
        if (Objects.isNull(s)) return null;

        var value = String.valueOf(s);

        return Stream.of(Gender.valueOf(value))
                .filter(g -> g.getValue().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
