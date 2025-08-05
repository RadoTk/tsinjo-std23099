package com.example.tsinjo.repository;

import com.example.tsinjo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatus(String status);
}
