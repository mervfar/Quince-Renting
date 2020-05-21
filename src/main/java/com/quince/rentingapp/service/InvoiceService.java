package com.quince.rentingapp.service;

import com.quince.rentingapp.domain.Utils;
import com.quince.rentingapp.domain.invoice.Invoice;
import com.quince.rentingapp.domain.invoice.InvoiceAddDTO;
import com.quince.rentingapp.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final CurrentUserService currentUser;



    public Map<String,String> saveInvoice(InvoiceAddDTO invoiceAddDTO){
        HashMap<String,String> result =new HashMap<>();
        Invoice newInvoice =Utils.mapper(invoiceAddDTO,Invoice.class);
        newInvoice.setUser(currentUser.getCurrentUser());
        newInvoice.setRentingTime(calculateDate(invoiceAddDTO.getRentingStart(),invoiceAddDTO.getRentingEnd()));
        long totalFee= Long.parseLong(newInvoice.getRentingTime())*Long.parseLong(newInvoice.getCarFee());
        newInvoice.setTotalFee(Long.toString(totalFee));
        invoiceRepository.save(newInvoice);
        result.put("info","Invoice has been created!");
        return result;
    }
    private String calculateDate(String dateTime1,String dateTime2){
        LocalDate d1 = LocalDate.parse(dateTime1, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDate d2 = LocalDate.parse(dateTime2, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        Long diffDays = diff.toDays();
        return diffDays.toString();
    }
}
