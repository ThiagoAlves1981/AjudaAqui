package com.AjudaAqui.trabalhoFinalDAW.repository;

import com.AjudaAqui.trabalhoFinalDAW.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
 * AOT generated JPA repository implementation for {@link ProdutoRepository}.
 */
@Generated
public class ProdutoRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public ProdutoRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link ProdutoRepository#findByNomeContainingIgnoreCase(java.lang.String,org.springframework.data.domain.Pageable)}.
   */
  public Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable) {
    String queryString = "SELECT p FROM Produto p WHERE UPPER(p.nome) LIKE UPPER(:nome) ESCAPE '\\'";
    String countQueryString = "SELECT COUNT(p) FROM Produto p WHERE UPPER(p.nome) LIKE UPPER(:nome) ESCAPE '\\'";
    Pageable pageable_1 = pageable != null ? pageable : Pageable.unpaged();
    if (pageable_1.getSort().isSorted()) {
      DeclaredQuery declaredQuery = DeclaredQuery.jpqlQuery(queryString);
      queryString = rewriteQuery(declaredQuery, pageable_1.getSort(), Produto.class);
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

    return PageableExecutionUtils.getPage((List<Produto>) query.getResultList(), pageable_1, countAll);
  }
}
