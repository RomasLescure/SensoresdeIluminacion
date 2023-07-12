package com.example.myapplication;


public class Datos {
    private int Id;
    private String Titulo;
    private String Detalle;
    private String RB1, RB2,RB3,RB4;

    //usar alt + Insert para generar los métodos AQUÍ
    public Datos(int id, String titulo, String detalle, String rb1,String rb2,String rb3,String rb4) {
        Id = id;
        Titulo = titulo;
        Detalle = detalle;
        RB1 =rb1;
        RB2 =rb2;
        RB3 =rb3;
        RB4 =rb4;

    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getTitulo() {
        return Titulo;
    }
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }
    public String getDetalle() {
        return Detalle;
    }
    public void setDetalle(String detalle) {
        Detalle = detalle;
    }



    public String getRB1() {
        return RB1;
    }
    public void setRB1(String rb1) {
        RB1 = rb1;
    }


    public String getRB2() {
        return RB2;
    }
    public void setRB2(String rb2) {
        RB2 = rb2;
    }

    public String getRB3() {
        return RB3;
    }
    public void setRB3(String rb3) {
        RB3 = rb3;
    }

    public String getRB4() {
        return RB4;
    }
    public void setRB4(String rb4) {
        RB4 = rb4;
    }


}
