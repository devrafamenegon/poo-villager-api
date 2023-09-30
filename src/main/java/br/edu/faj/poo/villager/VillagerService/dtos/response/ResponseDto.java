package br.edu.faj.poo.villager.VillagerService.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String message;
    private boolean status;
    private Object data;
}
