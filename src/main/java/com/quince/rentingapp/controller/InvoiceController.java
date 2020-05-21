package com.quince.rentingapp.controller;

import com.google.api.client.util.Lists;
import com.quince.rentingapp.domain.Utils;
import com.quince.rentingapp.domain.car.Car;
import com.quince.rentingapp.domain.car.CarViewDTO;
import com.quince.rentingapp.domain.invoice.Invoice;
import com.quince.rentingapp.domain.invoice.InvoiceAddDTO;
import com.quince.rentingapp.domain.invoice.InvoiceViewDTO;
import com.quince.rentingapp.domain.user.User;
import com.quince.rentingapp.service.CurrentUserService;
import com.quince.rentingapp.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final CurrentUserService currentUserService;

    @GetMapping("/save")
    public InvoiceAddDTO addPrepare(){
        return new InvoiceAddDTO();
    }

    @PostMapping("/save")
    public Map<String,String> createInvoice(@RequestBody InvoiceAddDTO addDTO){
        return  invoiceService.saveInvoice(addDTO);
    }
    @PostMapping("/byUser")
    public List<InvoiceViewDTO> userInvoices(){
        return convertToViewDTO(currentUserService.getCurrentUser().getInvoiceList());
    }
    private List<InvoiceViewDTO> convertToViewDTO(List<Invoice> invoiceList){
        List<InvoiceViewDTO> InvoiceViewDTOs= Lists.newArrayList();
        for (Invoice invoice:invoiceList){
            InvoiceViewDTOs.add(Utils.mapper(invoice,InvoiceViewDTO.class));
        }
        return InvoiceViewDTOs;
    }
}
