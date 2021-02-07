package com.example.lastattempts.repos;

import com.example.lastattempts.domain.Transaction;
import com.example.lastattempts.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

}