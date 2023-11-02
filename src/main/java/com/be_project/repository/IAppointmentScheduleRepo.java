package com.be_project.repository;

import com.be_project.entity.AppointmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentScheduleRepo extends JpaRepository<AppointmentSchedule, Long> {
    AppointmentSchedule findByExchangeId(long exchangeId);
}
