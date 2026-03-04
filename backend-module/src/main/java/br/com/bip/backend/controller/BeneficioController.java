package br.com.bip.backend.controller;

import br.com.bip.backend.beneficio.BeneficioAppService;
import br.com.bip.backend.dto.BeneficioDto;
import br.com.bip.backend.dto.TransferRequest;
import br.com.bip.domain.BeneficioEntity;
import br.com.bip.domain.exception.EjbException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/beneficios")
public class BeneficioController {

    private final BeneficioAppService service;

    public BeneficioController(BeneficioAppService service) {
        this.service = service;
    }

    @GetMapping
    public List<BeneficioEntity> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public BeneficioEntity findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeneficioEntity create(@Valid @RequestBody BeneficioDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public BeneficioEntity update(@PathVariable Long id, @Valid @RequestBody BeneficioDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@Valid @RequestBody TransferRequest req) {
        try {
            service.transfer(req);
        } catch (EjbException e) {
            throw new RuntimeException(e);
        }
    }
}
