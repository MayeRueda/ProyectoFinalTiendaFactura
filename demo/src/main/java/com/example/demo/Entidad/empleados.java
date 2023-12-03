package com.example.demo.Entidad;

import jakarta.persistence.*;
@Entity
@Table(name = "empleados")

public class empleados {
    @Id
    @Column(name = "cc_Empleado")

    private Integer cc_Empleado;
    @Column(nullable = false, length = 100)
    private String nomEmpleado;

    @Column(nullable = false, length = 100)
    private int salario_Empleado;
    @Column(nullable = false, length = 100)
    private String horario;
    @Column(nullable = false, length = 100)
    private String cargo;

    public empleados() {
    }

    public empleados(Integer cc_Empleado, String nomEmpleado, int salario_Empleado, String horario, String cargo) {
        this.cc_Empleado = cc_Empleado;
        this.nomEmpleado = nomEmpleado;
        this.salario_Empleado = salario_Empleado;
        this.horario = horario;
        this.cargo = cargo;
    }

    public Integer getCc_Empleado() {
        return cc_Empleado;
    }

    public void setCc_Empleado(Integer cc_Empleado) {
        this.cc_Empleado = cc_Empleado;
    }

    public String getNomEmpleado() {
        return nomEmpleado;
    }

    public void setNomEmpleado(String nomEmpleado) {
        this.nomEmpleado = nomEmpleado;
    }

    public int getSalario_Empleado() {
        return salario_Empleado;
    }

    public void setSalario_Empleado(int salario_Empleado) {
        this.salario_Empleado = salario_Empleado;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}