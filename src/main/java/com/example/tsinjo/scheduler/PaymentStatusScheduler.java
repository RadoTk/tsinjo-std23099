package com.example.tsinjo.scheduler;

import com.example.tsinjo.client.VolaClient;
import com.example.tsinjo.model.Payment;
import com.example.tsinjo.repository.PaymentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentStatusScheduler {

    private final PaymentRepository paymentRepository;
    private final VolaClient volaClient;

    public PaymentStatusScheduler(PaymentRepository paymentRepository, VolaClient volaClient) {
        this.paymentRepository = paymentRepository;
        this.volaClient = volaClient;
    }

    @Scheduled(fixedRate = 60000) // toutes les 60 secondes
    public void updatePendingPayments() {
        List<Payment> verifyingPayments = paymentRepository.findByStatus("VERIFYING");

        for (Payment payment : verifyingPayments) {
            String newStatus = volaClient.checkPaymentStatus(payment.getRef());
            if (!newStatus.equals(payment.getStatus())) {
                payment.setStatus(newStatus);
                paymentRepository.save(payment);
                System.out.println("✅ Paiement " + payment.getRef() + " mis à jour : " + newStatus);
            }
        }
    }
}
