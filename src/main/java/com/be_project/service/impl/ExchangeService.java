package com.be_project.service.impl;

import com.be_project.entity.Exchange;
import com.be_project.repository.IExchangeRepo;
import com.be_project.service.IExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService implements IExchangeService {
    @Autowired
    private IExchangeRepo exchangeRepo;
    @Override
    public Exchange saveExchange(Exchange exchange) {
        return exchangeRepo.save(exchange);
    }
}
