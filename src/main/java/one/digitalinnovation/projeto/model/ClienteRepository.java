package one.digitalinnovation.projeto.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Interface para prover todos os métodos de acesso a dados
// referentes a entidade cliente. Usamos uma strategy: CrudRepository Interface
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
