package com.dio.project.BoardProject.Entities;


import com.dio.project.BoardProject.Enum.ColumnType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table
public class BoardColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ColumnType columnType;

    @ManyToOne
    @JoinColumn(name = "board_id") //? Identificador na tabela para a FK.
    private Board board;

    @OneToMany(mappedBy = "boardColumn")
    private ArrayList<Card> cards;
}
