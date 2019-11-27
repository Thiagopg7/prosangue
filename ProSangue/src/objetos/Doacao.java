/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.sql.Timestamp;

/**
 *
 * @author PMPALMEIRA15
 */
public class Doacao {

    private int id;
    private int fkDoador;
    private boolean hepatiteB;
    private boolean hepatiteC;
    private boolean chagas;
    private boolean sifilis;
    private boolean aids;
    private boolean htlv;
    private boolean teste_anemia;
    private boolean triagem_clinica;
    private Timestamp horario;
    private String imunohematologia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkDoador() {
        return fkDoador;
    }

    public void setFkDoador(int fkDoador) {
        this.fkDoador = fkDoador;
    }

    public boolean isHepatiteB() {
        return hepatiteB;
    }

    public void setHepatiteB(boolean hepatiteB) {
        this.hepatiteB = hepatiteB;
    }

    public boolean isHepatiteC() {
        return hepatiteC;
    }

    public void setHepatiteC(boolean hepatiteC) {
        this.hepatiteC = hepatiteC;
    }

    public boolean isChagas() {
        return chagas;
    }

    public void setChagas(boolean chagas) {
        this.chagas = chagas;
    }

    public boolean isSifilis() {
        return sifilis;
    }

    public void setSifilis(boolean sifilis) {
        this.sifilis = sifilis;
    }

    public boolean isAids() {
        return aids;
    }

    public void setAids(boolean aids) {
        this.aids = aids;
    }

    public boolean isHtlv() {
        return htlv;
    }

    public void setHtlv(boolean htlv) {
        this.htlv = htlv;
    }

    public boolean isTeste_anemia() {
        return teste_anemia;
    }

    public void setTeste_anemia(boolean teste_anemia) {
        this.teste_anemia = teste_anemia;
    }

    public boolean isTriagem_clinica() {
        return triagem_clinica;
    }

    public void setTriagem_clinica(boolean triagem_clinica) {
        this.triagem_clinica = triagem_clinica;
    }

    public Timestamp getHorario() {
        return horario;
    }

    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

    public String getImunohematologia() {
        return imunohematologia;
    }

    public void setImunohematologia(String imunohematologia) {
        this.imunohematologia = imunohematologia;
    }

}