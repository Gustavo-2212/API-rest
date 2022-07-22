package one.digitalinnovation.projeto.service.impl;

import one.digitalinnovation.projeto.model.Cliente;
import one.digitalinnovation.projeto.model.ClienteRepository;
import one.digitalinnovation.projeto.model.Endereco;
import one.digitalinnovation.projeto.model.EnderecoRepository;
import one.digitalinnovation.projeto.service.ClienteService;
import one.digitalinnovation.projeto.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ViaCepService viaCepService;
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBD = clienteRepository.findById(id);

        if (clienteBD.isPresent() ) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        // .orElseGet() : caso não encontre o cep no banco, faça o
        // que o callback implementa. Callback é a expressão lambda
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // consumindo a api cep
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
