package com.tienda.demo.Entidad;


import jakarta.persistence.*;

@Entity
@Table(name="usuarios")
public class Auth0 {

    @Id
private String email;
    @Column(name="name")
    private  String name;
@Column(name="nick", unique = true)
private String nick;

@Column(name="img")
private String img;

@Column(name="auth_id", unique = true)
private String auth_id;

@Column(name="rol")
private String rol;




    @ManyToOne
    @JoinColumn(name = "UsuarioID")
    private User user;

    @Column(name = "tipoderol")
    private String tipoderol;

    public Auth0() {
    }

    public Auth0(String email, String nick, String img, String auth_id, String rol) {
        this.email = email;
        this.nick = nick;
        this.img = img;
        this.auth_id = auth_id;
        this.rol=rol;
    }
    public Auth0(String email, String name, String img, String auth_id, User user, String tipoderol) {
        this.email = email;
        this.name = name;
        this.img = img;
        this.auth_id = auth_id;
        this.user = user;
        this.tipoderol = tipoderol;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTipoderol() {
        return tipoderol;
    }

    public void setTipoderol(String tipoderol) {
        this.tipoderol = tipoderol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
