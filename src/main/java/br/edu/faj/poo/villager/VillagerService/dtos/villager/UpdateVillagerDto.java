package br.edu.faj.poo.villager.VillagerService.dtos.villager;

import lombok.Data;

@Data
public class UpdateVillagerDto {
    private String name;
    private String email;
    private Integer block;
    private Integer apartment;
}