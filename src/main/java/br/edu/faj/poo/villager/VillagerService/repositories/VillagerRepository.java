package br.edu.faj.poo.villager.VillagerService.repositories;

import br.edu.faj.poo.villager.VillagerService.dtos.villager.CreateVillagerDto;
import br.edu.faj.poo.villager.VillagerService.dtos.villager.GetVillagerDto;
import br.edu.faj.poo.villager.VillagerService.dtos.villager.UpdateVillagerDto;
import br.edu.faj.poo.villager.VillagerService.entities.Villager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VillagerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Villager create(Villager villager) throws Exception {
        String sqlInsert = "INSERT INTO VILLAGER (NAME, EMAIL, BLOCK, APARTMENT) VALUES (?, ?, ?, ?)";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(
                        sqlInsert,
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            ps.setString(1, villager.getName());
            ps.setString(2, villager.getEmail());
            ps.setInt(3, villager.getBlock());
            ps.setInt(4, villager.getApartment());

            int result = ps.executeUpdate();
            if (result == 1) {
                ResultSet tableKeys = ps.getGeneratedKeys();
                tableKeys.next();

                villager.setId(tableKeys.getInt(1));
                System.out.println("Villager inserido com sucesso: " + villager.getName());
                return villager;
            }
            throw new Exception("Erro ao inserir no banco.");
        }
    }

    public Villager getById(Long id) {
        String sqlSelect = "SELECT * FROM VILLAGER WHERE ID = ?";
        Villager villager = null;

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelect)
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                villager = new Villager();
                villager.setId(rs.getInt("ID"));
                villager.setName(rs.getString("NAME"));
                villager.setEmail(rs.getString("EMAIL"));
                villager.setBlock(rs.getInt("BLOCK"));
                villager.setApartment(rs.getInt("APARTMENT"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return villager;
    }

    public List<Villager> getAll() {
        String sqlSelectAll = "SELECT * FROM VILLAGER";
        List<Villager> villagers = new ArrayList<>();

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelectAll)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Villager villager = new Villager();
                villager.setId(rs.getInt("ID"));
                villager.setName(rs.getString("NAME"));
                villager.setEmail(rs.getString("EMAIL"));
                villager.setBlock(rs.getInt("BLOCK"));
                villager.setApartment(rs.getInt("APARTMENT"));
                villagers.add(villager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return villagers;
    }

    public Villager update(Integer id, Villager villager) throws Exception {
        String sqlUpdate = "UPDATE VILLAGER SET NAME=?, EMAIL=?, BLOCK=?, APARTMENT=? WHERE ID=?";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlUpdate)
        ) {
            ps.setString(1, villager.getName());
            ps.setString(2, villager.getEmail());
            ps.setInt(3, villager.getBlock());
            ps.setInt(4, villager.getApartment());
            ps.setInt(5, id);

            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Villager atualizado com sucesso");

                villager.setId(id);
                return villager;
            }
            throw new Exception("Erro ao atualizar no banco.");
        }
    }

    public void delete(Long id) throws Exception {
        String sqlDelete = "DELETE FROM VILLAGER WHERE ID=?";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlDelete)
        ) {
            ps.setLong(1, id);

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new Exception("Erro ao excluir no banco.");
            }
            System.out.println("Villager excluído com sucesso: " + id);
        } catch (SQLException e) {
            throw new Exception("Erro ao excluir no banco.", e);
        }
    }
}
