package com.dio.project.BoardProject.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "board") //? Nome do atributo na outra classe.
    private List<BoardColumn> boardColumns;


    public Board(String name, List<BoardColumn> columns){
        this.name = name;
        this.boardColumns = columns;
    }
}
