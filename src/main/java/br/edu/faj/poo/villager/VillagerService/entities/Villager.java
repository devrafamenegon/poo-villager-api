package br.edu.faj.poo.villager.VillagerService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Villager {
    private Integer id;
    private String name;
    private String email;
    private Integer block;
    private Integer apartment;
}

