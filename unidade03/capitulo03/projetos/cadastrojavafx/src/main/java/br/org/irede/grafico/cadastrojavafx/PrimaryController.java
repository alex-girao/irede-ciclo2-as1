package br.org.irede.grafico.cadastrojavafx;

import br.org.irede.grafico.cadastrojavafx.model.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Classe evocada para administrar eventos da tela primary.fxml
 * @author alexgirao
 */
public class PrimaryController {
    
    //componentes
    @FXML
    private TextField txtNome;
    
    @FXML
    private TableView<Pessoa> tableNomes;
    
    @FXML
    private TableColumn<Pessoa, Integer> colId;
    
    @FXML
    private TableColumn<Pessoa, String> colNome;
    
    //observador, para atualizacao da tabela
    private final ObservableList<Pessoa> listaPessoa = 
        FXCollections.observableArrayList();
    
    //variaveis de apoio
    private int proximoId = 1;
    private Pessoa pessoaSelecionada;
    
    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableNomes.setItems(listaPessoa);
        
        tableNomes.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo)-> {
            pessoaSelecionada = novo;
            if(novo != null){
                txtNome.setText(novo.getNome());
            }
        });
    }
    
    @FXML
    private void adicionarNome(){
        String nome = txtNome.getText().trim();
        if(nome.isEmpty()){
            mostrarAlerta("Digite um nome válido");
            //interrompe a execução do método
            return;
        }
        listaPessoa.add(new Pessoa(proximoId++, nome));
        limparCampos();
    }
    
    @FXML
    private void limparCampos(){
        txtNome.clear();
    }
    
    @FXML
    private void removerNome(){
        if(pessoaSelecionada == null){
            mostrarAlerta("Selecione um registro para remover");
            return;
        }
        listaPessoa.remove(pessoaSelecionada);
        limparCampos();
    }
    
    @FXML
    private void atualizarNome(){
        if(pessoaSelecionada == null){
            mostrarAlerta("Selecione um registro para atualizar");
            return;
        }
        String nome = txtNome.getText().trim();
        if(nome.isEmpty()){
            mostrarAlerta("Digite um nome válido");
            //interrompe a execução do método
            return;
        }
        pessoaSelecionada.setNome(nome);
        tableNomes.refresh();
        limparCampos();
    }

    private void mostrarAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Atenção!");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

}
