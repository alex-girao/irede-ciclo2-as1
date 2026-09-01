package br.org.irede.grafico.cadastrojavafx.model;

// Diferente do Java "puro" (que usaria int e String direto), o JavaFX tem
// seu próprio sistema de propriedades observáveis. Isso é o que permite
// que a UI se atualize SOZINHA quando o dado muda, sem você escrever
// código manual de "atualizar tela".
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Classe de modelo (Model) no padrão MVC.
 * Representa UMA pessoa do cadastro — cada linha de uma TableView, por
 * exemplo, vai corresponder a um objeto Pessoa como esse.
 *
 * A GRANDE diferença pra uma classe Java "comum" (POJO tradicional) é que
 * os atributos aqui NÃO são int/String puros — são "Property", que são
 * como caixinhas observáveis: qualquer componente JavaFX pode "escutar"
 * mudanças nelas.
 */
public class Pessoa {

    // 'final' porque a PROPRIEDADE em si nunca muda de objeto — o que muda
    // é o VALOR guardado dentro dela (via .set()). É um erro comum achar
    // que precisa recriar a Property toda vez que o valor muda; não precisa!
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nome;

    /**
     * Construtor "de conveniência": recebe tipos primitivos normais (int,
     * String) — assim quem usa a classe Pessoa no resto do código não
     * precisa entender de Property, só passa os valores comuns.
     * Por baixo dos panos, a gente "embrulha" cada valor na sua Property.
     */
    public Pessoa(int id, String nome){
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
    }

    // ===== Getters e Setters "tradicionais" =====
    // Servem pra quem só quer ler/gravar o VALOR cru, sem se preocupar
    // com o mecanismo de Property por trás. É o jeito "compatível" de
    // usar a classe em qualquer código Java comum (ex: enviar pro banco).
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }

    // ===== O "Property Getter" — a parte MÁGICA do padrão JavaFX Bean =====
    // Por convenção OBRIGATÓRIA, o nome do método deve ser
    // "<atributo>Property()" — é assim que o JavaFX (e ferramentas como
    // PropertyValueFactory da TableView) DESCOBREM automaticamente, via
    // reflection, que existe uma propriedade observável chamada "id".
    //
    // É esse método que você usa quando quer, por exemplo, vincular uma
    // coluna de TableView direto ao dado:
    //   colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
    // Isso só funciona PORQUE existe o idProperty() aqui embaixo.
    public SimpleIntegerProperty idProperty() { return id; }

    public String getNome() { return nome.get(); }
    public void setNome(String nome) { this.nome.set(nome); }

    // Mesma lógica do idProperty(): habilita a coluna "nome" da TableView
    // a se atualizar sozinha se o valor mudar via setNome() em qualquer
    // lugar do sistema — inclusive fora da tela.
    public SimpleStringProperty nomeProperty() { return nome; }

}