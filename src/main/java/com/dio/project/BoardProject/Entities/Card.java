package com.dio.project.BoardProject.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "column_id")
    private BoardColumn boardColumn;
}
