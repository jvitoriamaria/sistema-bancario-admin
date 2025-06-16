/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.FuncionarioDao;
import com.unincor.sistema.bancario.admin.model.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class FuncionarioService {
    
    private static Funcionario funcionario;

    private final FuncionarioDao funcionarioDao = new FuncionarioDao();

    public void salvarFuncionario(Funcionario funcionario) throws CadastroException, SQLException {
        if (funcionario.getNome()== null || funcionario.getNome().isBlank()) {
            throw new CadastroException("Funcionario não possui um nome.");
        }

        if (funcionario.getCpf() == null || funcionario.getCpf().isBlank()) {
            throw new CadastroException("Funcionario não possui um cpf informado!!");
        }
        funcionarioDao.inserirFuncionario(funcionario);
    }

    public List<Funcionario> buscarGerentes() {
        return funcionarioDao.buscarTodosFuncionarios();
    }

    public static void main(String[] args) throws SQLException {
        FuncionarioService funcionarioService = new FuncionarioService();
        Funcionario funcionario = new Funcionario(null, null);

        try {
            funcionarioService.salvarFuncionario(funcionario);
        } catch (CadastroException ex) {
            Logger.getLogger(AgenciaService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
