package ru.clevertec.ecl.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.clevertec.ecl.entity.House;
import ru.clevertec.ecl.entity.Sex;
import ru.clevertec.ecl.util.ConstFormatDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPersonDTO {

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String passportSeries;
    private Long passportNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstFormatDate.FORMAT)
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstFormatDate.FORMAT)
    private LocalDateTime updateDate;

    @Builder.Default
    private List<House> houseList = new ArrayList<>();

    @Builder.Default
    private House house = new House();
}
