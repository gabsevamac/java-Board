package com.dio.project.BoardProject.Repositories;

import com.dio.project.BoardProject.Entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long > {
}
