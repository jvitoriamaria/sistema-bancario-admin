/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.dao;

import com.unincor.sistema.bancario.admin.configurations.MySQL;
import com.unincor.sistema.bancario.admin.model.domain.Funcionario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class FuncionarioDao {

    private List<Funcionario> funcionarios;
    private long idFuncionario;

    public void inserirFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios(nome, cpf, data_nascimento, email,telefone, senha_hash, turno) values (?,?,?,?,?,?,?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
            ps.setString(4, funcionario.getEmail());
            ps.setString(5, funcionario.getTelefone());
            ps.setString(6, funcionario.getSenhaHash());
            ps.setString(7, funcionario.getTurno());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "Select * from funcionarios";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var funcionario = construirFuncionarioSql(rs);
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionarios;
    }

    public Funcionario buscarFuncionarioPorId(Long idCliente) {
        String sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idFuncionario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirFuncionarioSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Funcionario construirFuncionarioSql(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(rs.getLong("id_funcionario"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        funcionario.setEmail(rs.getString("email"));
        funcionario.setTelefone(rs.getString("telefone"));
        funcionario.setSenhaHash(rs.getString("senha_hash"));
        funcionario.setTurno(rs.getString("turno"));
        return funcionario;
    }

    public static void main(String[] args) {
        FuncionarioDao funcionarioDao = new FuncionarioDao();

        Funcionario novof = new Funcionario();
        novof.setNome("Julio Borges");
        novof.setCpf("45678951212");
        novof.setDataNascimento(LocalDate.of(2004, 8, 29));
        novof.setEmail("julioborges23@gmail.com");
        novof.setTelefone("35994756321");
        novof.setSenhaHash("256245gsH");
        novof.setTurno("Noite");
        
        funcionarioDao.inserirFuncionario(novof);
        System.out.println("Funcionario inserido.");

    }

}