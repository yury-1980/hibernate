package ru.clevertec.ecl.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.ecl.dto.requestDTO.RequestPersonDTO;
import ru.clevertec.ecl.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.ecl.service.PersonService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/persons")
@AllArgsConstructor
@RestController
public class PersonController {

    private PersonService services;

    @GetMapping
    public List<ResponsePersonDTO> getAllHouse(@RequestParam(defaultValue = "1") int pageNumber,
                                               @RequestParam(defaultValue = "5") int pageSize) {
        return services.findByAll(pageNumber, pageSize);
    }

    @GetMapping("/{uuid}")
    public ResponsePersonDTO getPerson(@PathVariable("uuid") UUID uuid) throws Throwable {

        return services.findByUUID(uuid);
    }

    @PostMapping
    public UUID addHouse(@RequestBody RequestPersonDTO requestPersonDTO) {

        return services.create(requestPersonDTO);
    }
}
