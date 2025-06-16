/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.domain;

/**
 *
 * @author maria
 */
public class Funcionario extends Pessoa {
    
         private Long idFuncionario;
     private String turno;


    public Funcionario() {
    }

    public Funcionario(Long idFuncionario, String turno) {
        this.idFuncionario = idFuncionario;
        this.turno = turno;
    }
    

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
       this.turno = turno;
    }
}
