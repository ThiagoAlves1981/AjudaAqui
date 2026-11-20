package com.AjudaAqui.trabalhoFinalDAW.repository;

import com.AjudaAqui.trabalhoFinalDAW.model.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Long;
import java.lang.String;
import java.util.List;
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
 * AOT generated JPA repository implementation for {@link PedidoRepository}.
 */
@Generated
public class PedidoRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public PedidoRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link PedidoRepository#findByClienteCompradorId(java.lang.Long,org.springframework.data.domain.Pageable)}.
   */
  public Page<Pedido> findByClienteCompradorId(Long clienteId, Pageable pageable) {
    String queryString = "SELECT p FROM Pedido p WHERE p.clienteComprador.id = :clienteId";
    String countQueryString = "SELECT COUNT(p) FROM Pedido p WHERE p.clienteComprador.id = :clienteId";
    Pageable pageable_1 = pageable != null ? pageable : Pageable.unpaged();
    if (pageable_1.getSort().isSorted()) {
      DeclaredQuery declaredQuery = DeclaredQuery.jpqlQuery(queryString);
      queryString = rewriteQuery(declaredQuery, pageable_1.getSort(), Pedido.class);
    }
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("clienteId", clienteId);
    if (pageable_1.isPaged()) {
      query.setFirstResult(Long.valueOf(pageable_1.getOffset()).intValue());
      query.setMaxResults(pageable_1.getPageSize());
    }
    LongSupplier countAll = () -> {
      Query countQuery = this.entityManager.createQuery(countQueryString);
      countQuery.setParameter("clienteId", clienteId);
      return getCount(countQuery);
    };

    return PageableExecutionUtils.getPage((List<Pedido>) query.getResultList(), pageable_1, countAll);
  }

  /**
   * AOT generated implementation of {@link PedidoRepository#findByClienteCompradorNomeContainingIgnoreCase(java.lang.String,org.springframework.data.domain.Pageable)}.
   */
  public Page<Pedido> findByClienteCompradorNomeContainingIgnoreCase(String nome,
      Pageable pageable) {
    String queryString = "SELECT p FROM Pedido p LEFT JOIN p.clienteComprador c WHERE UPPER(c.nome) LIKE UPPER(:nome) ESCAPE '\\'";
    String countQueryString = "SELECT COUNT(p) FROM Pedido p LEFT JOIN p.clienteComprador c WHERE UPPER(c.nome) LIKE UPPER(:nome) ESCAPE '\\'";
    Pageable pageable_1 = pageable != null ? pageable : Pageable.unpaged();
    if (pageable_1.getSort().isSorted()) {
      DeclaredQuery declaredQuery = DeclaredQuery.jpqlQuery(queryString);
      queryString = rewriteQuery(declaredQuery, pageable_1.getSort(), Pedido.class);
    }
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("nome", "%%%s%%".formatted(nome != null ? nome.toUpperCase() : nome));
    if (pageable_1.isPaged()) {
      query.setFirstResult(Long.valueOf(pageable_1.getOffset()).intValue());
      query.setMaxResults(pageable_1.getPageSize());
    }
    LongSupplier countAll = () -> {
      Query countQuery = this.entityManager.createQuery(countQueryString);
      countQuery.setParameter("nome", "%%%s%%".formatted(nome != null ? nome.toUpperCase() : nome));
      return getCount(countQuery);
    };

    return PageableExecutionUtils.getPage((List<Pedido>) query.getResultList(), pageable_1, countAll);
  }
}
