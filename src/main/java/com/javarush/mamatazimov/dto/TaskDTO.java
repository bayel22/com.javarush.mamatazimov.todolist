package com.javarush.mamatazimov.dto;

import com.javarush.mamatazimov.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskDTO {
    private String description;
    private Status status;
}
