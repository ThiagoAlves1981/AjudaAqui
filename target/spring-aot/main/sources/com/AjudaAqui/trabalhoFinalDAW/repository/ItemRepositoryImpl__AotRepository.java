package com.AjudaAqui.trabalhoFinalDAW.repository;

import com.AjudaAqui.trabalhoFinalDAW.model.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.LongSupplier;
import org.springframework.aot.generate.Generated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.DeclaredQuery;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.query.Param;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * AOT generated JPA repository implementation for {@link ItemRepository}.
 */
@Generated
public class ItemRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public ItemRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link ItemRepository#findByProdutoNomeContainingIgnoreCase(java.lang.String,org.springframework.data.domain.Pageable)}.
   */
  public Page<Item> findByProdutoNomeContainingIgnoreCase(String nome, Pageable pageable) {
    String queryString = "SELECT i FROM Item i LEFT JOIN i.produto p WHERE UPPER(p.nome) LIKE UPPER(:nome) ESCAPE '\\'";
    String countQueryString = "SELECT COUNT(i) FROM Item i LEFT JOIN i.produto p WHERE UPPER(p.nome) LIKE UPPER(:nome) ESCAPE '\\'";
    Pageable pageable_1 = pageable != null ? pageable : Pageable.unpaged();
    if (pageable_1.getSort().isSorted()) {
      DeclaredQuery declaredQuery = DeclaredQuery.jpqlQuery(queryString);
      queryString = rewriteQuery(declaredQuery, pageable_1.getSort(), Item.class);
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

    return PageableExecutionUtils.getPage((List<Item>) query.getResultList(), pageable_1, countAll);
  }

  /**
   * AOT generated implementation of {@link ItemRepository#somarTotalDoPedido(java.lang.Long)}.
   */
  public BigDecimal somarTotalDoPedido(@Param("pedidoId") Long pedidoId) {
    String queryString = "SELECT SUM(i.quantidade * i.precoUnitario) FROM Item i WHERE i.pedido.id = :pedidoId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("pedidoId", pedidoId);

    return (BigDecimal) convertOne(query.getSingleResultOrNull(), false, BigDecimal.class);
  }
}
