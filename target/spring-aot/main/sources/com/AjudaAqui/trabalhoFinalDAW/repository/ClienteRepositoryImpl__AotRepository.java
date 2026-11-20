package com.AjudaAqui.trabalhoFinalDAW.repository;

import com.AjudaAqui.trabalhoFinalDAW.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import java.util.function.LongSupplier;
import org.springframework.aot.generate.Generated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.DeclaredQuery;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * AOT generated JPA repository implementation for {@link ClienteRepository}.
 */
@Generated
public class ClienteRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public ClienteRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link ClienteRepository#findByCpf(java.lang.String)}.
   */
  public Optional<Cliente> findByCpf(String cpf) {
    String queryString = "SELECT c FROM Cliente c WHERE c.cpf = :cpf";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("cpf", cpf);

    return Optional.ofNullable((Cliente) convertOne(query.getSingleResultOrNull(), false, Cliente.class));
  }

  /**
   * AOT generated implementation of {@link ClienteRepository#findByNomeContainingIgnoreCaseOrCpfContaining(java.lang.String,java.lang.String,org.springframework.data.domain.Pageable)}.
   */
  public Page<Cliente> findByNomeContainingIgnoreCaseOrCpfContaining(String nome, String cpf,
      Pageable pageable) {
    String queryString = "SELECT c FROM Cliente c WHERE UPPER(c.nome) LIKE UPPER(:nome) ESCAPE '\\' OR c.cpf LIKE :cpf ESCAPE '\\'";
    String countQueryString = "SELECT COUNT(c) FROM Cliente c WHERE UPPER(c.nome) LIKE UPPER(:nome) ESCAPE '\\' OR c.cpf LIKE :cpf ESCAPE '\\'";
    Pageable pageable_1 = pageable != null ? pageable : Pageable.unpaged();
    if (pageable_1.getSort().isSorted()) {
      DeclaredQuery declaredQuery = DeclaredQuery.jpqlQuery(queryString);
      queryString = rewriteQuery(declaredQuery, pageable_1.getSort(), Cliente.class);
    }
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("nome", "%%%s%%".formatted(nome != null ? nome.toUpperCase() : nome));
    query.setParameter("cpf", "%%%s%%".formatted(cpf));
    if (pageable_1.isPaged()) {
      query.setFirstResult(Long.valueOf(pageable_1.getOffset()).intValue());
      query.setMaxResults(pageable_1.getPageSize());
    }
    LongSupplier countAll = () -> {
      Query countQuery = this.entityManager.createQuery(countQueryString);
      countQuery.setParameter("nome", "%%%s%%".formatted(nome != null ? nome.toUpperCase() : nome));
      countQuery.setParameter("cpf", "%%%s%%".formatted(cpf));
      return getCount(countQuery);
    };

    return PageableExecutionUtils.getPage((List<Cliente>) query.getResultList(), pageable_1, countAll);
  }
}
