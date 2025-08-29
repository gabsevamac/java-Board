package com.dio.project.BoardProject.Controller;

import com.dio.project.BoardProject.DTO.CreateBoardRequest;
import com.dio.project.BoardProject.Entities.Board;
import com.dio.project.BoardProject.Exceptions.DataNotFoundException;
import com.dio.project.BoardProject.Services.BoardService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController("board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("")
    public ResponseEntity<List<Object>> getBoards(){
        try{
            return ResponseEntity.ok(Collections.singletonList(boardService.getBoards()));
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/${id}")
    public ResponseEntity<Object> getBoard(@PathParam("id") Long id){
        try{
            return ResponseEntity.ok(boardService.getBoard(id));
        }catch (DataNotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createBoard(@RequestBody CreateBoardRequest board){
        try{
            boardService.createBoard(board);
            return ResponseEntity.ok("Board created successfully!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
