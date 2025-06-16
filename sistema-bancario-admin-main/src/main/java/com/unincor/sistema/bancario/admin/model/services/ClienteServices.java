/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.ClienteDao;
import com.unincor.sistema.bancario.admin.model.domain.Cliente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class ClienteServices {

    private static Cliente cliente;

    private final ClienteDao clienteDao = new ClienteDao();

    public void salvarCliente(Cliente cliente) throws CadastroException {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new CadastroException("Cliente não possui um nome informado.");
        }

        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new CadastroException("Cliente não possui um CPF informado.");
        }

        clienteDao.inserirCliente(cliente);
    }

    public List<Cliente> buscarClientes() {
        return clienteDao.buscarTodosClientes();
    }

    public static void main(String[] args) throws Exception {
      ClienteServices ClienteService = new ClienteServices();
      Cliente cliente = new Cliente(null, null);
      try{
          ClienteService.salvarCliente(cliente);
        } catch (CadastroException ex){
            Logger.getLogger(ClienteServices.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
   

