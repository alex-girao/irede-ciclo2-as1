package br.org.irede.grafico.cadastrojavafx;

import javafx.application.Application; // Classe-mãe de toda aplicação JavaFX. Sem estender ela, não existe app gráfico.
import javafx.fxml.FXMLLoader;          // Responsável por ler o arquivo .fxml (XML) e transformar em objetos Java reais na tela.
import javafx.scene.Parent;             // Tipo genérico pra "raiz" de uma árvore de componentes visuais (qualquer layout: VBox, AnchorPane, etc).
import javafx.scene.Scene;              // Representa o conteúdo de uma janela: o que está sendo desenhado ali dentro.
import javafx.stage.Stage;              // Representa a JANELA em si (moldura, título, botão de fechar). Scene fica "dentro" do Stage.
import java.io.IOException;

/**
 * JavaFX App
 *
 * Estrutura padrão gerada pelo arquétipo "javafx:jfx-maven-archetype" ou pelo
 * próprio NetBeans quando você cria um projeto JavaFX com FXML.
 * O padrão aqui é: 1 classe App = ponto de entrada + gerenciador de troca de telas.
 */
public class App extends Application {

    // 'static' aqui é proposital: guardamos a referência da Scene atual como
    // atributo da CLASSE (não da instância) porque o método setRoot() também
    // é estático — é um jeito de trocar o conteúdo da tela de qualquer lugar
    // do código sem precisar carregar uma instância de App.
    // Cuidado: em apps maiores isso vira "estado global" e pode virar dor de
    // cabeça pra manter; em apps pequenos/didáticos é aceitável.
    private static Scene scene;

    /**
     * start() é o método OBRIGATÓRIO de toda Application JavaFX.
     * Ele é chamado automaticamente pela JVM/JavaFX runtime DEPOIS de
     * inicializar a plataforma gráfica — nunca chame start() manualmente.
     *
     * O Stage (a janela) já vem PRONTO como parâmetro, criado pelo próprio
     * framework. Você só precisa configurar o que vai DENTRO dele.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Carrega o "primary.fxml" e cria a Scene com tamanho fixo 640x480.
        // Repare que o construtor de Scene já recebe a raiz (Parent) pronta.
        scene = new Scene(loadFXML("primary"), 640, 480);

        stage.setScene(scene); // A janela (Stage) recebe o conteúdo (Scene)
        stage.show();          // Só agora a janela realmente aparece na tela!
                                // Sem o show(), tudo fica montado na memória mas invisível.
    }

    /**
     * Método utilitário para TROCAR de tela sem abrir uma janela nova.
     * Em vez de criar um novo Stage, a gente troca só o "root" (a raiz do
     * layout) dentro da MESMA Scene/Stage já existentes.
     * Isso é o padrão recomendado pra navegação simples em apps JavaFX+FXML
     * (ex: sair da tela de login e ir pra tela principal).
     *
     * 'static' porque 'scene' também é static — mantém a mesma lógica.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carrega um arquivo .fxml e devolve a árvore de componentes (Parent)
     * pronta pra ser usada como root de uma Scene.
     *
     * App.class.getResource(...) busca o arquivo dentro do CLASSPATH,
     * na mesma pasta/pacote da classe App (por isso é importante que o
     * "primary.fxml" esteja em src/main/resources/.../cadastrojavafx/).
     *
     * Convenção: o parâmetro "fxml" é só o NOME do arquivo, sem extensão —
     * por isso concatenamos ".fxml" aqui dentro.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load(); // Aqui acontece a "mágica": o XML vira objetos Java de verdade
    }

    /**
     * Ponto de entrada padrão do Java (main). Só chama launch(), que é
     * quem de fato inicializa toda a plataforma JavaFX (thread própria,
     * o famoso "JavaFX Application Thread") e, por baixo dos panos,
     * chama o start() lá em cima.
     *
     * IMPORTANTE: nunca chame start() diretamente — sempre via launch().
     * E lembrando: main() TEM que retornar void, senão a JVM recusa a
     * classe como ponto de entrada válido (causa clássica de erro chato
     * de debugar, como você mesmo já viu por aqui 😉).
     */
    public static void main(String[] args) {
        launch();
    }
}