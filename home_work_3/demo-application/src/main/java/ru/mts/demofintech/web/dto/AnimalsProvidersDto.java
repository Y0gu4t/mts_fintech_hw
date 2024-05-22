package ru.mts.demofintech.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalsProvidersDto {
    private int typeId;
    private int providerId;
}
