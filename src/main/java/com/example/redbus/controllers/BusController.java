 package com.example.redbus.controllers;

import com.example.redbus.models.Bus;
import com.example.redbus.models.Seat;
import com.example.redbus.repository.BusRepository;
import com.example.redbus.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusRepository busRepo;

    @Autowired
    private SeatRepository seatRepo;

    @GetMapping("/all")
    public List<Bus> getAllBuses() {
        return busRepo.findAll();
    }

    @GetMapping("/{busId}")
    public Optional<Bus> getBusById(@PathVariable Long busId) {
        return busRepo.findById(busId);
    }

    @PostMapping("/add")
    public Bus addBus(@RequestBody Bus bus) {
        return busRepo.save(bus);
    }

    @GetMapping("/{busId}/seats")
    public List<Seat> getSeatsByBus(@PathVariable Long busId) {
        return seatRepo.findByBusId(busId);
    }

    @PostMapping("/seat/book/{seatId}")
    public Seat bookSeat(@PathVariable Long seatId, @RequestParam String gender) {
        Seat seat = seatRepo.findById(seatId).orElseThrow(() -> new RuntimeException("Seat not found"));
        if (seat.isBooked()) throw new RuntimeException("Seat already booked!");
        seat.setBooked(true);
        seat.setGender(gender);
        return seatRepo.save(seat);
    }

    @DeleteMapping("/delete/{busId}")
    public String deleteBus(@PathVariable Long busId) {
        if (busRepo.existsById(busId)) {
            busRepo.deleteById(busId);
            return "Bus deleted!";
        }
        return "Bus not found!";
    }
}
