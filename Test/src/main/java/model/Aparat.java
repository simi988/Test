package model;

import java.time.LocalDate;
import java.util.Date;

public class Aparat {
    private int idAutorizatie;
    private int idAparatFix;
    private int idOperator;
    private String clasa;
    private int nrCerereAutorizatie;
    private int nrDecizie;
    private LocalDate dtSolicitareAutorizatie;
    private LocalDate dtDecizie;
    private LocalDate dtInceputAutorizatie;
    private LocalDate dtSfarsitAutorizatie;
    private int prelungeste;
    private LocalDate dtServer;
    private String serie;

    public Aparat( int idAparatFix, int idOperator, String clasa, int nrCerereAutorizatie, int nrDecizie, LocalDate dtSolicitareAutorizatie, LocalDate dtDecizie, LocalDate dtInceputAutorizatie, LocalDate dtSfarsitAutorizatie, int prelungeste, LocalDate dtServer) {
        this.idAutorizatie = idAutorizatie;
        this.idAparatFix = idAparatFix;
        this.idOperator = idOperator;
        this.clasa = clasa;
        this.nrCerereAutorizatie = nrCerereAutorizatie;
        this.nrDecizie = nrDecizie;
        this.dtSolicitareAutorizatie = dtSolicitareAutorizatie;
        this.dtDecizie = dtDecizie;
        this.dtInceputAutorizatie = dtInceputAutorizatie;
        this.dtSfarsitAutorizatie = dtSfarsitAutorizatie;
        this.prelungeste = prelungeste;
        this.dtServer = dtServer;
    }

    public Aparat( int idAparatFix, int idOperator, String clasa, LocalDate dtSolicitareAutorizatie, LocalDate dtDecizie, LocalDate dtInceputAutorizatie, LocalDate dtSfarsitAutorizatie, LocalDate dtServer) {
        this.idAutorizatie = idAutorizatie;
        this.idAparatFix = idAparatFix;
        this.idOperator = idOperator;
        this.clasa = clasa;
        this.dtSolicitareAutorizatie = dtSolicitareAutorizatie;
        this.dtDecizie = dtDecizie;
        this.dtInceputAutorizatie = dtInceputAutorizatie;
        this.dtSfarsitAutorizatie = dtSfarsitAutorizatie;
        this.dtServer = dtServer;
    }

    public Aparat() {

    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getIdAutorizatie() {
        return idAutorizatie;
    }

    public void setIdAutorizatie(int idAutorizatie) {
        this.idAutorizatie = idAutorizatie;
    }

    public int getIdAparatFix() {
        return idAparatFix;
    }

    public void setIdAparatFix(int idAparatFix) {
        this.idAparatFix = idAparatFix;
    }

    public int getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(int idOperator) {
        this.idOperator = idOperator;
    }

    public String getClasa() {
        return clasa;
    }

    public void setClasa(String clasa) {
        this.clasa = clasa;
    }

    public int getNrCerereAutorizatie() {
        return nrCerereAutorizatie;
    }

    public void setNrCerereAutorizatie(int nrCerereAutorizatie) {
        this.nrCerereAutorizatie = nrCerereAutorizatie;
    }

    public int getNrDecizie() {
        return nrDecizie;
    }

    public void setNrDecizie(int nrDecizie) {
        this.nrDecizie = nrDecizie;
    }

    public LocalDate getDtSolicitareAutorizatie() {
        return dtSolicitareAutorizatie;
    }

    public void setDtSolicitareAutorizatie(LocalDate dtSolicitareAutorizatie) {
        this.dtSolicitareAutorizatie = dtSolicitareAutorizatie;
    }

    public LocalDate getDtDecizie() {
        return dtDecizie;
    }

    public void setDtDecizie(LocalDate dtDecizie) {
        this.dtDecizie = dtDecizie;
    }

    public LocalDate getDtInceputAutorizatie() {
        return dtInceputAutorizatie;
    }

    public void setDtInceputAutorizatie(LocalDate dtInceputAutorizatie) {
        this.dtInceputAutorizatie = dtInceputAutorizatie;
    }

    public LocalDate getDtSfarsitAutorizatie() {
        return dtSfarsitAutorizatie;
    }

    public void setDtSfarsitAutorizatie(LocalDate dtSfarsitAutorizatie) {
        this.dtSfarsitAutorizatie = dtSfarsitAutorizatie;
    }

    public int getPrelungeste() {
        return prelungeste;
    }

    public void setPrelungeste(int prelungeste) {
        this.prelungeste = prelungeste;
    }

    public LocalDate getDtServer() {
        return dtServer;
    }

    public void setDtServer(LocalDate dtServer) {
        this.dtServer = dtServer;
    }
}
