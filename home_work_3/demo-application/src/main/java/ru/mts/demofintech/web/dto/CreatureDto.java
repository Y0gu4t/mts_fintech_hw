package ru.mts.demofintech.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatureDto {
    private long id;
    private String name;
    private int typeId;
    private short age;
}
