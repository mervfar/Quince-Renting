package com.quince.rentingapp.controller;

import com.quince.rentingapp.domain.invoice.InvoiceAddDTO;
import com.quince.rentingapp.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("/save")
    public InvoiceAddDTO addPrepare(){
        return new InvoiceAddDTO();
    }

    @PostMapping("/save")
    public Map<String,String> createInvoice(@RequestBody InvoiceAddDTO addDTO){
        return  invoiceService.saveInvoice(addDTO);
    }
}
