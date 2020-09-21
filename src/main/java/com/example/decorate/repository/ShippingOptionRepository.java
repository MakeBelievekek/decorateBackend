package com.example.decorate.repository;

import com.example.decorate.domain.PaymentOption;
import com.example.decorate.domain.ShippingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingOptionRepository extends JpaRepository<ShippingOption, Long> {

   // @Query("select s.paymentOptionList from ShippingOption s where s.id =:id ")
    //List<PaymentOption> findAllPaymentOption(@Param("id") Long id);
}
