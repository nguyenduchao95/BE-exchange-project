package com.be_project.service;

import com.be_project.entity.AppointmentSchedule;

public interface IAppointmentScheduleService {
    AppointmentSchedule getByExchangeId(long exchangeId);
    AppointmentSchedule createSchedule(AppointmentSchedule appointmentSchedule);
}
