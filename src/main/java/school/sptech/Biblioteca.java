package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro){
        if(
            livro == null ||
            livro.getTitulo() == null ||
            livro.getTitulo().isBlank() ||
            livro.getAutor() == null ||
            livro.getAutor().isBlank() ||
            livro.getDataPublicacao() == null
        ){
            throw new ArgumentoInvalidoException("Argumento(s) inválido(s)!");
        }

        this.livros.add(livro);
    }

    public void removerLivroPorTitulo(String titulo){
        Livro livro = this.buscarLivroPorTitulo(titulo);

        this.livros.remove(livro);
    }

    public Livro buscarLivroPorTitulo(String titulo){
        if(titulo == null || titulo.isBlank()){
            throw new ArgumentoInvalidoException("Argumento(s) inválido(s)!");
        }

        for(Livro livro : livros){
            if(livro.getTitulo().equalsIgnoreCase(titulo)){
                return livro;
            }
        }

        throw new LivroNaoEncontradoException("Livro não encontrado!");
    }

    public Integer contarLivros(){
        return livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano){
        if(ano == null){
            throw new ArgumentoInvalidoException("Argumento inválido!");
        }

        List<Livro> livrosObtidos = new ArrayList<>();

        for(Livro livro : livros){
            if(livro.getDataPublicacao().getYear() <= ano){
                livrosObtidos.add(livro);
            }
        }

        return livrosObtidos;
    }

    public List<Livro> retornarTopCincoLivros(){
        if(livros.isEmpty()) {
            return livros;
        }

        // Ordenar os livros na lista de forma decrescente
        for (int i = 0; i < livros.size(); i++) {
            for (int j = i + 1; j < livros.size(); j++) {
                Double media1 = livros.get(i).calcularMediaAvaliacoes();
                Double media2 = livros.get(j).calcularMediaAvaliacoes();

                if(media1 < media2){
                    Livro livro = livros.get(i);

                    livros.set(i, livros.get(j));
                    livros.set(j, livro);
                }
            }
        }

        List<Livro> livrosSelecionados = new ArrayList<>();
        Integer max = 5;

        if(livros.size() < max) {
            max = livros.size();
        }

        for (int i = 0; i < max; i++) {
            livrosSelecionados.add(livros.get(i));
        }

        return livrosSelecionados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
