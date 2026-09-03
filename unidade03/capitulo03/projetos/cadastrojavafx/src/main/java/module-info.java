module br.org.irede.grafico.cadastrojavafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.org.irede.grafico.cadastrojavafx to javafx.fxml;
    opens br.org.irede.grafico.cadastrojavafx.model to javafx.base;
    exports br.org.irede.grafico.cadastrojavafx;
    
}
