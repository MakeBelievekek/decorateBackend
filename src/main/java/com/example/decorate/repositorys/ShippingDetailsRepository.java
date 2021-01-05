package com.example.decorate.repositorys;

import com.example.decorate.domain.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails, Long> {
    @Query("select p from ShippingDetails p where p.orderHistory.id =:id")
    ShippingDetails findByOrderId(Long id);
}
