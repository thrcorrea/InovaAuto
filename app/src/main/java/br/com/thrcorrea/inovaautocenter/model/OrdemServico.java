package br.com.thrcorrea.inovaautocenter.model;

/**
 * Created by thale on 30/11/2016.
 */

public class OrdemServico {

    private String numeroOs;
    private String status;
    private String carro;
    private String data;

    public OrdemServico() {
    }

    public OrdemServico(String numeroOs, String status, String carro, String data) {
        this.numeroOs = numeroOs;
        this.status = status;
        this.carro = carro;
        this.data = data;
    }

    public String getNumeroOs() {
        return numeroOs;
    }

    public void setNumeroOs(String numeroOs) {
        this.numeroOs = numeroOs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarro() {
        return carro;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrdemServico{" +
                "numeroOs='" + numeroOs + '\'' +
                ", status='" + status + '\'' +
                ", carro='" + carro + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
