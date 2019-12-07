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
    private int hepatiteB;
    private int hepatiteC;
    private int chagas;
    private int sifilis;
    private int aids;
    private int htlv;
    private int testeAnemia;
    private int triagemClinica;
    private Timestamp horario;

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

    public int getHepatiteB() {
        return hepatiteB;
    }

    public void setHepatiteB(int hepatiteB) {
        this.hepatiteB = hepatiteB;
    }

    public int getHepatiteC() {
        return hepatiteC;
    }

    public void setHepatiteC(int hepatiteC) {
        this.hepatiteC = hepatiteC;
    }

    public int getChagas() {
        return chagas;
    }

    public void setChagas(int chagas) {
        this.chagas = chagas;
    }

    public int getSifilis() {
        return sifilis;
    }

    public void setSifilis(int sifilis) {
        this.sifilis = sifilis;
    }

    public int getAids() {
        return aids;
    }

    public void setAids(int aids) {
        this.aids = aids;
    }

    public int getHtlv() {
        return htlv;
    }

    public void setHtlv(int htlv) {
        this.htlv = htlv;
    }

    public int getTesteAnemia() {
        return testeAnemia;
    }

    public void setTesteAnemia(int testeAnemia) {
        this.testeAnemia = testeAnemia;
    }

    public int getTriagemClinica() {
        return triagemClinica;
    }

    public void setTriagemClinica(int triagemClinica) {
        this.triagemClinica = triagemClinica;
    }

    public Timestamp getHorario() {
        return horario;
    }

    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

}