package com.dio.project.BoardProject.Enum;

public enum ColumnType {
    INICIAL(1),
    CANCELAMENTO(4),
    FINAL(3),
    PENDENTE(2);

    private final int orderPriority;

    ColumnType(int orderPriority){
        this.orderPriority = orderPriority;
    }
}
