package com.javarush.mamatazimov.dto;

import com.javarush.mamatazimov.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String description;
    private Status status;
}
