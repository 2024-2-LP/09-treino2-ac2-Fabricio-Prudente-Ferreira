package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private List<Avaliacao> avaliacoes;

    public Livro(String titulo, String autor, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.avaliacoes = new ArrayList<>();
    }

    public void adicionarAvaliacao(String descricao, Double qtdEstrela){
        if(
            descricao == null ||
            qtdEstrela == null ||
            descricao.isBlank() ||
            qtdEstrela < 0.0 ||
            qtdEstrela > 5.0
        ){
            throw new ArgumentoInvalidoException("Argumento(s) inválido(s)!");
        }

        Avaliacao avaliacao = new Avaliacao(descricao, qtdEstrela);

        this.avaliacoes.add(avaliacao);
    }

    public Double calcularMediaAvaliacoes(){
        if(!avaliacoes.isEmpty()){
            Double total = 0.0;

            for(Avaliacao avaliacao : avaliacoes){
                total += avaliacao.getQtdEstrelas();
            }

            Double media = total / avaliacoes.size();

            return media;
        }

        return 0.0;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                '}';
    }
}
