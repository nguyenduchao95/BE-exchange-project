package com.be_project.service.impl;

import com.be_project.entity.Exchange;
import com.be_project.repository.IExchangeRepo;
import com.be_project.service.IExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExchangeService implements IExchangeService {
    @Autowired
    private IExchangeRepo exchangeRepo;
    @Override
    public Exchange saveExchange(Exchange exchange) {
        return exchangeRepo.save(exchange);
    }

    @Override
    public Exchange denyExchange(Exchange exchange) {
        exchange.setStatus("Đã hủy");
        return exchangeRepo.save(exchange);
    }

    @Override
    public Page<Exchange> getAllByAccountId(long accountId, String status, String postSell, String postBuy, LocalDate startDate, LocalDate endDate, int page, int size) {
        if (startDate == null) startDate = LocalDate.parse("1000-01-01");

        if (endDate == null) endDate = LocalDate.parse("9999-12-31");
        return exchangeRepo.getAllByAccountId(accountId, status, postSell, postBuy, startDate, endDate, PageRequest.of(page, size));
    }
}
