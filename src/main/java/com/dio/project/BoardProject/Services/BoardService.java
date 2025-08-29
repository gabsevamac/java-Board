package com.dio.project.BoardProject.Services;

import com.dio.project.BoardProject.DTO.CreateBoardRequest;
import com.dio.project.BoardProject.Entities.Board;
import com.dio.project.BoardProject.Entities.BoardColumn;
import com.dio.project.BoardProject.Entities.Card;
import com.dio.project.BoardProject.Enum.ColumnType;
import com.dio.project.BoardProject.Exceptions.DataNotFoundException;
import com.dio.project.BoardProject.Repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardService {
    private final BoardRepository repository;
    private final CardService cardService;
    private final BoardColumnService boardColumnService;

    @Autowired
    public BoardService(BoardRepository boardRepository, CardService cardService, BoardColumnService boardColumnService) {
        this.repository = boardRepository;
        this.cardService = cardService;
        this.boardColumnService = boardColumnService;
    }

    public void createBoard(CreateBoardRequest req){
        var columns = req.columns().stream().toList();
        if(!isColumnListValid(columns)){
            throw new RuntimeException("A initial, final and cancelled column are necessary!");
        }

        Board board = new Board(req.boardName(), columns);
        repository.save(board);
    }

    public Board getBoard(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Board not found!!"));
    }

    public List<Board> getBoards(){
        return repository.findAll();
    }

    public void deleteBoard(Board board){
        repository.delete(board);
    }

    public void changeCardColumn(Long cardId, boolean isCancelled){
        Card card = cardService.getCard(cardId);
        BoardColumn previousCardColumn = card.getBoardColumn();
        List<BoardColumn> columns = previousCardColumn.getBoard().getBoardColumns();
        //Removendo card da lista anterior
        var updatedCards = previousCardColumn.getCards().remove(card);
        boardColumnService.updateColumn(previousCardColumn);

        var newColumn = checkNextColumn(previousCardColumn, columns, isCancelled);
    }

    private BoardColumn checkNextColumn(BoardColumn previousCardColumn, List<BoardColumn> columns, boolean isCancelled){
        if(isCancelled){
            return columns.stream()
                    .filter(column -> column.getColumnType().equals(ColumnType.CANCELAMENTO))
                    .findFirst()
                    .orElseThrow(() -> new DataNotFoundException("Column not found!"));
        }
        int previousIndex = columns.indexOf(previousCardColumn);
        return columns.get(previousIndex + 1);

    }

    private boolean isColumnListValid(List<BoardColumn> columns){
        long initialCount = countColumnType(columns, ColumnType.INICIAL);
        long finalCount = countColumnType(columns, ColumnType.FINAL);
        long cancelledCount = countColumnType(columns, ColumnType.CANCELAMENTO);

        return initialCount == 0 || finalCount == 0 || cancelledCount == 0;
    }


    private long countColumnType(List<BoardColumn> columns, ColumnType columnType){
        return columns.stream().filter(
                (column) -> column.getColumnType().equals(columnType)
        ).count();
    }

}
