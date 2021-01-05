package com.example.decorate.repositorys;

import com.example.decorate.domain.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Long> {
    @Query("select p from BillingDetails p where p.orderHistory.id =:id")
    BillingDetails findByOrderId(Long id);
}
