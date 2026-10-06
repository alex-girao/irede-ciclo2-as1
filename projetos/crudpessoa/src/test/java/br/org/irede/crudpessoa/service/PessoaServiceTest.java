package br.org.irede.crudpessoa.service;

import br.org.irede.crudpessoa.model.Pessoa;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alex-girao
 */
public class PessoaServiceTest {
    
    private PessoaService service;
    
    @BeforeAll
    static void inicio(){
        System.out.println(">> Iniciando testes do CRUD de Pessoa");
    }
    
    @BeforeEach
    void setUp(){
        service = new PessoaService();
    }
    
    @AfterEach
    void tearDown(){
        service = null;
        System.out.println("   teste finalizado, service descartado");
    }
    
    @AfterAll
    static void fim(){
        System.out.println(">> Fim dos testes");
    }
    
    // CRIAR
    @Test
    void deveCadastrarPessoaValida(){
        final String NOME_TESTE = "Andre Dantas";
        final String CPF_TESTE = "12345678123";
        Pessoa pessoa = service.cadastrar(NOME_TESTE, CPF_TESTE);
        
        assertNotNull(pessoa.getId());
        assertEquals(NOME_TESTE, pessoa.getNome());
        assertEquals(CPF_TESTE, pessoa.getCpf());
    }
    
    @Test
    void naoDeveCadastrarSemNome(){
        assertThrows(IllegalArgumentException.class, 
                () -> service.cadastrar("", "67854312300"));
    }
    
    @Test
    void naoDeveCadastrarCpfComMenosDeOnzeDigitos(){
        assertThrows(IllegalArgumentException.class, 
                () -> service.cadastrar("Joao", "6785431230"));
    }
    
}
