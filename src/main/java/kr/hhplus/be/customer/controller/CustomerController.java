package kr.hhplus.be.customer.controller;

import kr.hhplus.be.customer.dto.CustomerDto;
import kr.hhplus.be.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }
}

