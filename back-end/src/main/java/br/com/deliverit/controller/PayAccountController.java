package br.com.deliverit.controller;

import br.com.deliverit.dto.PayAccountRequestDTO;
import br.com.deliverit.dto.PayAccountResponseDTO;
import br.com.deliverit.dto.CreatePayAccountResponseDTO;
import br.com.deliverit.service.PayAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pay-accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PayAccountController {

    private final PayAccountService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePayAccountResponseDTO create(@RequestBody PayAccountRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<PayAccountResponseDTO> list() {
        return service.list();
    }
}
