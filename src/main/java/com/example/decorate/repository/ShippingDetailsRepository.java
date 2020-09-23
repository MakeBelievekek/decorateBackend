package com.example.decorate.repository;

import com.example.decorate.domain.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails, Long> {
}
