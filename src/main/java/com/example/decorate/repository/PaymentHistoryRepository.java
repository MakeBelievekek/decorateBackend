package com.example.decorate.repository;

import com.example.decorate.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

    @Query("select p.id from PaymentHistory p where p.orderHistory.id =:id")
    Long findByOrderHistoryTest(@Param("id") Long id);


}
