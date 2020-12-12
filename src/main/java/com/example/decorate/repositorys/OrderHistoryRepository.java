package com.example.decorate.repositorys;

import com.example.decorate.domain.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    @Query("SELECT o FROM OrderHistory o ")
    List<OrderHistory> findAllOrderHistory();
}
