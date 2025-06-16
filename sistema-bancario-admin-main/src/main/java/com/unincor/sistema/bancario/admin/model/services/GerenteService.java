/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.GerenteDao;
import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import com.unincor.sistema.bancario.admin.model.domain.Gerente;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


/**
 *
 * @author maria
 */
public class GerenteService {

    private final GerenteDao gerenteDao = new GerenteDao();

    public void salvarGerente(Gerente gerente) throws CadastroException, SQLException {
        if (gerente.getNome() == null || gerente.getNome().isBlank()) {
            throw new CadastroException("Nome não pode estar vazio");
        }

        if (gerente.getCpf() == null || gerente.getCpf().isBlank()) {
            throw new CadastroException("CPF não pode estar vazio");
        }

        if (gerente.getAgencia() == null) {
            throw new CadastroException("Agência deve ser informada");
        }

        gerenteDao.inserirGerente(gerente);
    }

    public Gerente buscarGerentePorId(Long id) {
        return gerenteDao.buscarGerentePorId(id);
    }

    public List<Gerente> listarTodos() {
        return gerenteDao.listarTodosGerentes();
    }

 public static void main(String[] args) {
    GerenteService gerenteService = new GerenteService();

    Gerente gerente = new Gerente();
    gerente.setNome("Fernanda Lima");
    gerente.setCpf("30024115412");
    gerente.setDataNascimento(LocalDate.of(1980, 5, 20));
    gerente.setEmail("fernandaLima74@bank.com");
    gerente.setTelefone("5546213147");
    gerente.setSenhaHash("1S12423");

    Agencia agencia = new Agencia();
    agencia.setIdAgencia(1l); // Tem que existir no banco!
    gerente.setAgencia(agencia);

    try {
        gerenteService.salvarGerente(gerente);
        System.out.println("Gerente salvo com sucesso.");
    } catch (CadastroException | SQLException e) {
        System.out.println("Erro ao salvar gerente: " + e.getMessage());
    }
}
}
