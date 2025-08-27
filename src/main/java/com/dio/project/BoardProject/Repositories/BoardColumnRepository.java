package com.dio.project.BoardProject.Repositories;

import com.dio.project.BoardProject.Entities.BoardColumn;
import com.dio.project.BoardProject.Entities.Card;
import com.dio.project.BoardProject.Services.BoardColumnService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {
    @Modifying
    @Query("UPDATE BoardColumn bc " +
            "SET bc.cards = #{column.cards}," +
            "bc.name = #{column.name}," +
            "bc.board = #{column.board}\n" +
            "WHERE bc.id = #{column.id}")
    public void updateBoardColumnCards(@Param("column") BoardColumn column);
}
