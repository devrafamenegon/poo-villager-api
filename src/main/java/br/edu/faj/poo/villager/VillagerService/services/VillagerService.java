package br.edu.faj.poo.villager.VillagerService.services;

import br.edu.faj.poo.villager.VillagerService.dtos.villager.CreateVillagerDto;
import br.edu.faj.poo.villager.VillagerService.dtos.villager.GetVillagerDto;
import br.edu.faj.poo.villager.VillagerService.dtos.villager.UpdateVillagerDto;
import br.edu.faj.poo.villager.VillagerService.entities.Villager;
import br.edu.faj.poo.villager.VillagerService.repositories.VillagerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VillagerService {

    private final VillagerRepository villagerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VillagerService(VillagerRepository villagerRepository, ModelMapper modelMapper) {
        this.villagerRepository = villagerRepository;
        this.modelMapper = modelMapper;
    }

    public GetVillagerDto createVillager(CreateVillagerDto createVillagerDto) throws Exception {
        Villager villager = modelMapper.map(createVillagerDto, Villager.class);
        villagerRepository.create(villager);
        return modelMapper.map(villager, GetVillagerDto.class);
    }

    public GetVillagerDto getVillagerById(Long id) {
        Villager villager = villagerRepository.getById(id);
        return modelMapper.map(villager, GetVillagerDto.class);
    }

    public List<GetVillagerDto> getAllVillagers() {
        List<Villager> villagers = villagerRepository.getAll();
        return villagers.stream().map(villager -> modelMapper.map(villager, GetVillagerDto.class))
            .collect(Collectors.toList());
    }

    public GetVillagerDto updateVillager(Integer id, UpdateVillagerDto updateVillagerDto) throws Exception {
        Villager villager = modelMapper.map(updateVillagerDto, Villager.class);
        villagerRepository.update(id, villager);
        return modelMapper.map(villager, GetVillagerDto.class);
    }

    public void deleteVillager(Long id) throws Exception {
        villagerRepository.delete(id);
    }
}
