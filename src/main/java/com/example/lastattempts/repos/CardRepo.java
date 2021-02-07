package com.example.lastattempts.repos;

import com.example.lastattempts.domain.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepo extends CrudRepository<Card, Long> {

    List<Card> findByOwnerId(Long user_id);

    Card findByNumber(Long number);

}
