package com.dio.project.BoardProject.Entities;


import com.dio.project.BoardProject.Enum.ColumnType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@RequiredArgsConstructor
public class BoardColumn implements Comparable<BoardColumn> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ColumnType columnType;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "board_id") //? Identificador na tabela para a FK.
    private Board board;

    @NonNull
    @OneToMany(mappedBy = "boardColumn")
    private List<Card> cards;

    @Override
    public int compareTo(BoardColumn o) {
        return this.getName().compareTo(o.getName());
    }
}
