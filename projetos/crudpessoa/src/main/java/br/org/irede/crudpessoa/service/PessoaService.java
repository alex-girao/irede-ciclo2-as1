package br.org.irede.crudpessoa.service;

import br.org.irede.crudpessoa.model.Pessoa;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * CRUD em memoria (sem banco de dados)
 * Regras:
 * - Nome obrigatório
 * - CPF com exatamente 11 digitos
 * - CPF não pode se repetir
 * 
 * @author alex-girao
 */
public class PessoaService {
    
    private final Map<Long, Pessoa> pessoas = new HashMap<>();
    private long proximoId = 1;

    public Pessoa cadastrar(String nome, String cpf) {
        validarNome(nome);
        validarCpf(cpf);
        for (Pessoa p : pessoas.values()) {
            if (p.getCpf().equals(cpf)) {
                throw new IllegalArgumentException("CPF ja cadastrado");
            }
        }
        Pessoa nova = new Pessoa(proximoId++, nome, cpf);
        pessoas.put(nova.getId(), nova);
        return nova;
    }

    public Pessoa buscarPorId(Long id) {
        Pessoa pessoa = pessoas.get(id);
        if (pessoa == null) {
            throw new IllegalArgumentException("Pessoa nao encontrada");
        }
        return pessoa;
    }

    public List<Pessoa> listar() {
        return new ArrayList<>(pessoas.values());
    }

    public Pessoa atualizarNome(Long id, String novoNome) {
        validarNome(novoNome);
        Pessoa pessoa = buscarPorId(id);
        pessoa.setNome(novoNome);
        return pessoa;
    }

    public void remover(Long id) {
        buscarPorId(id); // lanca excecao se nao existir
        pessoas.remove(id);
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome e obrigatorio");
        }
    }

    private void validarCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve ter 11 digitos numericos");
        }
    }
    
}
