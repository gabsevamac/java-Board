package com.dio.project.BoardProject.DTO;

import com.dio.project.BoardProject.Entities.BoardColumn;

import java.util.Set;

public record CreateBoardRequest(
        String boardName,
        Set<BoardColumn> columns
//        String initialColumnName,
//        String completedColumnName,
//        String cancelledColumnName,
//        Set<BoardColumn> pendents
) {
}
