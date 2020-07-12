package Model.Value;

import java.io.*;

public class Anuncios implements Serializable   {
  
  private String titulo,descricao,bairro,tipoAnuncio,tipo,area,URL,URL1,URL2,URL3,BI,idImovel,dataInicio;
  private int nrCasa,nrQuartos, quarteirao; 
  private float preco;
  private int andar,id, estacionamento;
  private boolean ativo;
  public Anuncios(){
     
  }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public String getBI() {
        return BI;
    }

    public void setBI(String BI) {
        this.BI = BI;
    }

    public String getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(String idImovel) {
        this.idImovel = idImovel;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getURL1() {
        return URL1;
    }

    public void setURL1(String URL1) {
        this.URL1 = URL1;
    }

    public String getURL2() {
        return URL2;
    }

    public void setURL2(String URL2) {
        this.URL2 = URL2;
    }

    public String getURL3() {
        return URL3;
    }

    public void setURL3(String URL3) {
        this.URL3 = URL3;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }


    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNrCasa() {
        return nrCasa;
    }

    public void setNrCasa(int nrCasa) {
        this.nrCasa = nrCasa;
    }

    public int getNrQuartos() {
        return nrQuartos;
    }

    public void setNrQuartos(int nrQuartos) {
        this.nrQuartos = nrQuartos;
    }

    public int getQuarteirao() {
        return quarteirao;
    }

    public void setQuarteirao(int quarteirao) {
        this.quarteirao = quarteirao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(int estacionamento) {
        this.estacionamento = estacionamento;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setId(int aInt) {
        this.id= aInt;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Anuncios{" + "titulo=" + titulo + ", descricao=" + descricao + ", bairro=" + bairro + ", tipoAnuncio=" + tipoAnuncio + ", tipo=" + tipo + ", area=" + area + ", URL=" + URL + ", URL1=" + URL1 + ", URL2=" + URL2 + ", URL3=" + URL3 + ", BI=" + BI + ", idImovel=" + idImovel + ", dataInicio=" + dataInicio + ", nrCasa=" + nrCasa + ", nrQuartos=" + nrQuartos + ", quarteirao=" + quarteirao + ", preco=" + preco + ", andar=" + andar + ", id=" + id + ", estacionamento=" + estacionamento + '}';
    }
    
    

    
    
}  