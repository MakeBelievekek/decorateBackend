package com.example.decorate.repositorys;

import com.example.decorate.domain.ShippingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingOptionRepository extends JpaRepository<ShippingOption, Long> {

    // @Query("select s.paymentOptionList from ShippingOption s where s.id =:id ")
    //List<PaymentOption> findAllPaymentOption(@Param("id") Long id);

    ShippingOption findByTypeOfDelivery(String type);
}
