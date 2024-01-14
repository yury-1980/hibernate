package ru.clevertec.ecl.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.ecl.entity.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestHouseDTO {

    private UUID uuid;
    private String area;
    private String country;
    private String city;
    private String street;
    private Long number;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createDate;

    @Builder.Default
    private List<Person> ownerList = new ArrayList<>();

    @Builder.Default
    private List<Person> residentsList = new ArrayList<>();
}
