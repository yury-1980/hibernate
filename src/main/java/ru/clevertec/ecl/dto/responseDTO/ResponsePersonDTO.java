package ru.clevertec.ecl.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponsePersonDTO {

    private UUID uuid;
    private String name;
    private String surname;
    private String sex;
    private String passportSeries;
    private Long passportNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updateDate;

  /*  //    @Builder.Default
    private List<House> houseList;// = new ArrayList<>();

    //    @Builder.Default
    private House house;// = new House();*/
}
