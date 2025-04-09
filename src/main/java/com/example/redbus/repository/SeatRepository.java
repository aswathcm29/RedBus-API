package com.example.redbus.repository;

import com.example.redbus.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByBusId(Long busId);
    List<Seat> findByBusIdAndBookedFalse(Long busId);
    List<Seat> findByBusIdAndBookedTrue(Long busId);
}
