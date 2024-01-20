package ru.clevertec.ecl.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.ecl.dto.requestDTO.RequestHouseDTO;
import ru.clevertec.ecl.dto.responseDTO.ResponseHouseDTO;
import ru.clevertec.ecl.service.HouseService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/houses")
@AllArgsConstructor
@RestController
public class HouseController {

    private HouseService services;

    @GetMapping
    public List<ResponseHouseDTO> getAllHouse(@RequestParam(defaultValue = "1") int pageNumber,
                                              @RequestParam(defaultValue = "15") int pageSize) {
        return services.findByAll(pageNumber, pageSize);
    }

    @GetMapping("/{uuid}")
    public ResponseHouseDTO getHouse(@PathVariable("uuid") UUID uuid) throws Throwable {

        return services.findByUUID(uuid);
    }

    @PostMapping
    public UUID addHouse(@RequestBody RequestHouseDTO requestHouseDTO) {

        return services.create(requestHouseDTO);
    }

    @PutMapping("/{uuid}")
    public void update(@RequestBody RequestHouseDTO requestHouseDTO, @PathVariable("uuid") UUID uuid) {
        services.update(requestHouseDTO, uuid);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable("uuid") UUID uuid) {
        services.delete(uuid);
    }
}
