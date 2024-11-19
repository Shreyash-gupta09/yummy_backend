package com.shreyash.yummy_new.controller;

import com.shreyash.yummy_new.dto.CustomerRequest;
import com.shreyash.yummy_new.dto.CustomerResponse;

import com.shreyash.yummy_new.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestHeader("Authorization") String authHeader,
                                                 @RequestBody CustomerRequest request) {
        String email = customerService.validateAndExtractEmail(authHeader);
        customerService.updateCustomer(email, request);
        return ResponseEntity.ok("Customer details updated successfully");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String authHeader) {
        String email = customerService.validateAndExtractEmail(authHeader);
        customerService.deleteCustomer(email);
        return ResponseEntity.ok("Customer account deleted successfully");
    }
}
