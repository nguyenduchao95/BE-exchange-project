package com.be_project.service.impl;

import com.be_project.entity.AppointmentSchedule;
import com.be_project.entity.Exchange;
import com.be_project.entity.Post;
import com.be_project.repository.IAppointmentScheduleRepo;
import com.be_project.repository.IExchangeRepo;
import com.be_project.repository.IPostRepo;
import com.be_project.service.IAppointmentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentScheduleService implements IAppointmentScheduleService {
    @Autowired
    private IAppointmentScheduleRepo appointmentScheduleRepo;
    @Autowired
    private IExchangeRepo exchangeRepo;
    @Autowired
    private IPostRepo postRepo;
    @Override
    public AppointmentSchedule getByExchangeId(long exchangeId) {
        return appointmentScheduleRepo.findByExchangeId(exchangeId);
    }

    @Override
    public AppointmentSchedule createSchedule(AppointmentSchedule appointmentSchedule) {
        Exchange exchange = exchangeRepo.findById(appointmentSchedule.getExchange().getId()).get();
        exchange.setStatus("Đã xác nhận");
        Post postSell = exchange.getPostSell();
        postSell.setStatus("Đã trao đổi");
        Post postBuy = exchange.getPostBuy();
        postBuy.setStatus("Đã trao đổi");
        postRepo.save(postSell);
        postRepo.save(postBuy);
        exchangeRepo.save(exchange);
        exchangeRepo.removeAll(postSell.getId(), postBuy.getId());
        return appointmentScheduleRepo.save(appointmentSchedule);
    }
}
