package com.be_project.repository;

import com.be_project.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExchangeRepo extends JpaRepository<Exchange, Long> {
}
