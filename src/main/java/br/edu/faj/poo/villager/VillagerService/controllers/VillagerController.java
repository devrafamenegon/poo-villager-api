package br.edu.faj.poo.villager.VillagerService.controllers;

import br.edu.faj.poo.villager.VillagerService.dtos.villager.CreateVillagerDto;
import br.edu.faj.poo.villager.VillagerService.dtos.villager.GetVillagerDto;
import br.edu.faj.poo.villager.VillagerService.dtos.villager.UpdateVillagerDto;
import br.edu.faj.poo.villager.VillagerService.dtos.response.ResponseDto;
import br.edu.faj.poo.villager.VillagerService.services.VillagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villagers")
@CrossOrigin(origins = "*")
public class VillagerController {

    private final VillagerService villagerService;

    @Autowired
    public VillagerController(VillagerService villagerService) {
        this.villagerService = villagerService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto> create(@RequestBody CreateVillagerDto body) {
        try {
            return ResponseEntity.ok(
                    new ResponseDto("Villager created successfully", true, villagerService.createVillager(body))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseDto> getAll() {
        try {
            List<GetVillagerDto> villagers = villagerService.getAllVillagers();
            return ResponseEntity.ok().body(
                    new ResponseDto("Villagers found", true, villagers)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
        try {
            GetVillagerDto villager = villagerService.getVillagerById(id);
            return ResponseEntity.ok().body(
                    new ResponseDto("Villager found", true, villager)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Integer id, @RequestBody UpdateVillagerDto body) {
        try {
            GetVillagerDto villager = villagerService.updateVillager(id, body);
            return ResponseEntity.ok().body(
                    new ResponseDto("Villager updated successfully", true, villager)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        try {
            villagerService.deleteVillager(id);
            return ResponseEntity.ok().body(
                    new ResponseDto("Villager deleted successfully", true, null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDto(e.getMessage(), false, null)
            );
        }
    }
}
