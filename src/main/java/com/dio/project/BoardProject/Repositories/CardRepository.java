package com.dio.project.BoardProject.Repositories;

import com.dio.project.BoardProject.Entities.Card;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Card c SET c.title = :#{#card.title()}, " +
            "c.description = :#{#card.description()}, " +
            "c.isBlocked = :#{#card.isBlocked()}" +
            "\nWHERE c.id = :#{#card.id()}")
    void updateCard(@Param("card")Card card);
}
