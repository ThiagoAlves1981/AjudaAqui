package com.AjudaAqui.trabalhoFinalDAW.service;

import com.AjudaAqui.trabalhoFinalDAW.repository.ProdutoRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ProdutoService}.
 */
@Generated
public class ProdutoService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'produtoService'.
   */
  private static BeanInstanceSupplier<ProdutoService> getProdutoServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ProdutoService>forConstructor(ProdutoRepository.class)
            .withGenerator((registeredBean, args) -> new ProdutoService(args.get(0)));
  }

  /**
   * Get the bean definition for 'produtoService'.
   */
  public static BeanDefinition getProdutoServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ProdutoService.class);
    beanDefinition.setInstanceSupplier(getProdutoServiceInstanceSupplier());
    return beanDefinition;
  }
}
