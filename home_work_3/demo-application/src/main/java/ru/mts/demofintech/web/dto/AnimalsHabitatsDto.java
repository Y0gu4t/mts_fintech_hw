package ru.mts.demofintech.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalsHabitatsDto {
    private int typeId;
    private int areaId;
}
