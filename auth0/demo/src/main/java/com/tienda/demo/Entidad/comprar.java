package com.tienda.demo.Entidad;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class comprar {
    // GeneratedValue se usa solo para la llave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_Compra;
    @Column(nullable = false, length = 80)
    private LocalDate fecha;
    @Column(nullable = false, length = 80)
    private LocalTime hora;
    @Column(nullable = false, length = 80)
    private Long total_compra;
    @Column(nullable = false, length = 80)
    private String iva;
    @Column(nullable = false, length = 80)
    private int pago;
    @Column(nullable = false, length = 80)
    private int vueltas;
    @Column(nullable = false, length = 80)
    private String dEstablecimiento;
    @Column(nullable = false, length = 80)
    private String metodoPago;
    @ManyToOne
    @JoinColumn(name = "cc_clientes", referencedColumnName = "cc_clientes")

    private clientes clientes;

    @ManyToOne
    @JoinColumn(name = "cc_Empleado", referencedColumnName = "cc_Empleado")

    private empleados empleados;
    @ManyToOne
    @JoinColumn(name = "codigodetalle", referencedColumnName = "cod_Identificacion")

    private detalles detalles;

    public comprar() {

    }

    public comprar(Long cod_Compra, LocalDate fecha, LocalTime hora, Long total_compra, String iva, int pago, int vueltas, String dEstablecimiento, String metodoPago, clientes clientes, empleados empleados, detalles detalles) {
        this.cod_Compra = cod_Compra;
        this.fecha = fecha;
        this.hora = hora;
        this.total_compra = total_compra;
        this.iva = iva;
        this.pago = pago;
        this.vueltas = vueltas;
        this.dEstablecimiento = dEstablecimiento;
        this.metodoPago = metodoPago;
        this.clientes = clientes;
        this.empleados = empleados;
        this.detalles = detalles;
    }

    public Long getCod_Compra() {
        return cod_Compra;
    }

    public void setCod_Compra(Long cod_Compra) {
        this.cod_Compra = cod_Compra;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Long getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(Long total_compra) {
        this.total_compra = total_compra;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public int getVueltas() {
        return vueltas;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public String getdEstablecimiento() {
        return dEstablecimiento;
    }

    public void setdEstablecimiento(String dEstablecimiento) {
        this.dEstablecimiento = dEstablecimiento;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public clientes getClientes() {
        return clientes;
    }

    public void setClientes(clientes clientes) {
        this.clientes = clientes;
    }

    public empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(empleados empleados) {
        this.empleados = empleados;
    }

    public detalles getDetalles() {
        return detalles;
    }

    public void setDetalles(detalles detalles) {
        this.detalles = detalles;
    }
}