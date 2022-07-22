package one.digitalinnovation.projeto.service;

import one.digitalinnovation.projeto.model.Cliente;

// Strategy com operações de CRUD
public interface ClienteService {
    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);
}
