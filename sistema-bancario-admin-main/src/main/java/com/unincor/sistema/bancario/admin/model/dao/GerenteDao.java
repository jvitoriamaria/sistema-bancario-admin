/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.dao;

import com.unincor.sistema.bancario.admin.configurations.MySQL;
import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import com.unincor.sistema.bancario.admin.model.domain.Gerente;
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

public class GerenteDao {

   
    public void inserirGerente(Gerente gerente) throws SQLException {
        String sql = "INSERT INTO gerentes(nome,cpf, data_nascimento, email, telefone,senha_hash, id_agencia) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gerente.getNome());
            ps.setString(2, gerente.getCpf());
            ps.setDate(3, Date.valueOf(gerente.getDataNascimento()));
            ps.setString(4, gerente.getEmail());
            ps.setString(5, gerente.getTelefone());
            ps.setString(6, gerente.getSenhaHash());
            ps.setLong(7, gerente.getAgencia().getIdAgencia());
            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public List<Gerente> listarTodosGerentes() {
    List<Gerente> gerentes = new ArrayList<>();
    String sql = "SELECT * FROM gerentes";
    try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            var gerente = construirGerenteSql(rs);
            gerentes.add(gerente);
        }
    } catch (SQLException ex) {
        Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return gerentes;
}

    

    public Gerente buscarGerentePorId(Long idGerente) {
        String sql = "SELECT * FROM gerentes where id_gerente = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idGerente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirGerenteSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Gerente construirGerenteSql(ResultSet rs) throws SQLException {
        Gerente gerente = new Gerente();
        Agencia agencia = new Agencia();

        gerente.setIdGerente(rs.getLong("id_gerente"));
        gerente.setNome(rs.getString("nome"));
        gerente.setCpf(rs.getString("cpf"));
        gerente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        gerente.setEmail(rs.getString("email"));
        gerente.setTelefone(rs.getString("telefone"));
        gerente.setSenhaHash(rs.getString("senha_hash"));

        agencia.setIdAgencia(rs.getLong("id_agencia"));
        gerente.setAgencia(agencia);
        return gerente;

    }

    public static void main(String[] args) throws SQLException {
        GerenteDao gerenteDao = new GerenteDao();
        Agencia agencia = new Agencia();
        agencia.setIdAgencia(1L);

        Gerente g = new Gerente();
        g.setNome("Fernada Souza");
        g.setCpf("47568953214");
        g.setDataNascimento(LocalDate.of(2008, 9, 12));
        g.setEmail("fernandabanck@gmail.com");
        g.setTelefone("9888564122");
        g.setSenhaHash("c45632");
        g.setAgencia(agencia);

        gerenteDao.inserirGerente(g);
        System.out.println("Gerente inserido.");

    }

}