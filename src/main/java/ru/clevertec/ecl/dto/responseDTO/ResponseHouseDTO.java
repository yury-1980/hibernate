package ru.clevertec.ecl.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseHouseDTO {

    private UUID uuid;
    private String area;
    private String country;
    private String city;
    private String street;
    private Long number;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createDate;
}
