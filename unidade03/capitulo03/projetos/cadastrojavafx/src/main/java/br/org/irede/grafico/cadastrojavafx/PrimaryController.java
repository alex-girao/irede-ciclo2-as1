package br.org.irede.grafico.cadastrojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Classe evocada para administrar eventos da tela primary.fxml
 * @author macmini
 */
public class PrimaryController {
    
    @FXML
    private TextField txtNome;
    
    @FXML
    private void adicionarNome(){
        String nome = txtNome.getText().trim();
        System.out.println(">>>> " + nome);
    }
    
    @FXML
    private void limparCampos(){
        txtNome.clear();
    }

}
