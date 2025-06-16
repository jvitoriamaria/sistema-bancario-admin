/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.domain;

import java.time.LocalDate;

/**
 *
 * @author maria 
 */
public class Cliente extends Pessoa {
    
    private Long idCliente;

    public Cliente() {
    }

    public Cliente(Long idCliente, String nome, String cpf, LocalDate dataNascimento,
            String email, String telefone, String senhaHash) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.senhaHash = senhaHash;
    }

    public Cliente(Object object, Object object0) {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }


}
