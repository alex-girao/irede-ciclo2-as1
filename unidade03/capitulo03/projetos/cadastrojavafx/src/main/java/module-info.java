module br.org.irede.grafico.cadastrojavafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.org.irede.grafico.cadastrojavafx to javafx.fxml;
    exports br.org.irede.grafico.cadastrojavafx;
}
