package com.example.decorate.repository;

import com.example.decorate.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

    @Query("select p from PaymentHistory p where p.orderHistory.id =:id")
    PaymentHistory findByOrderHistory(@Param("id") Long id);

    @Query("select p from PaymentHistory p where p.paymentId =:paymentId")
    PaymentHistory findByPaymentId(@Param("paymentId") String paymentId);
}
