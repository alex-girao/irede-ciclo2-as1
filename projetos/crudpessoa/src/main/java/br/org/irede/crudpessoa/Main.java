package br.org.irede.crudpessoa;

import br.org.irede.crudpessoa.model.Pessoa;
import br.org.irede.crudpessoa.service.PessoaService;

/**
 * 
 * @author alex-girao
 */
public class Main {

    public static void main(String[] args) {
        PessoaService service = new PessoaService();
        
        Pessoa joao = service.cadastrar("Joao Silva", "12345678901");
        Pessoa maria = service.cadastrar("Maria Souza", "98765432100");
        Pessoa jose = service.cadastrar("jose", "11122233344");
        Pessoa josefa = service.cadastrar("josefa", "11122233343");
        Pessoa josefina = service.cadastrar("josefina", "00099988811");
        Pessoa alex = service.cadastrar("alex", "00099988819");
        
        for(Pessoa p : service.listar()){
            System.out.println(p.getId() + ", " + p.getCpf() + ", " + p.getNome());
        }
    }
}
