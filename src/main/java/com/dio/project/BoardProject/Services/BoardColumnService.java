package com.dio.project.BoardProject.Services;

import com.dio.project.BoardProject.Entities.Board;
import com.dio.project.BoardProject.Entities.BoardColumn;
import com.dio.project.BoardProject.Entities.Card;
import com.dio.project.BoardProject.Enum.ColumnType;
import com.dio.project.BoardProject.Exceptions.DataNotFoundException;
import com.dio.project.BoardProject.Repositories.BoardColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardColumnService {
    private final BoardColumnRepository repository;

    @Autowired
    public BoardColumnService(BoardColumnRepository repository) {
        this.repository = repository;
    }

    public void createColumn(BoardColumn boardColumn){
        var columns = boardColumn.getBoard().getBoardColumns();
        if(checkColumnAvailability(columns, boardColumn.getColumnType())){
            repository.save(boardColumn);
        }else {
            throw new RuntimeException("Can only exists 1 initial, completed and cancelled column");
        }
    }

    public void deleteColumn(BoardColumn boardColumn){
        repository.delete(boardColumn);
    }

    public BoardColumn getColumnById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Column not found!!"));
    }

    public void updateColumn(BoardColumn column){
        repository.updateBoardColumnCards(column);
    }

    private boolean checkColumnAvailability(List<BoardColumn> columns, ColumnType createdColumnType){
        long count = 0;
        switch (createdColumnType){
            case FINAL ->  count = columns.stream().filter(
                    (BoardColumn column) -> column.getColumnType().equals(ColumnType.FINAL)
            ).count();
            case INICIAL -> count = columns.stream().filter(
                    (BoardColumn column) -> column.getColumnType().equals(ColumnType.INICIAL)
            ).count();
            case CANCELAMENTO -> count = columns.stream().filter(
                    (BoardColumn column) -> column.getColumnType().equals(ColumnType.CANCELAMENTO)
            ).count();
        }
        return count == 0;
    }
}
