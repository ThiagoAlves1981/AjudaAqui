package com.AjudaAqui.trabalhoFinalDAW.controller;

import com.AjudaAqui.trabalhoFinalDAW.service.ProdutoService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ProdutoController}.
 */
@Generated
public class ProdutoController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'produtoController'.
   */
  private static BeanInstanceSupplier<ProdutoController> getProdutoControllerInstanceSupplier() {
    return BeanInstanceSupplier.<ProdutoController>forConstructor(ProdutoService.class)
            .withGenerator((registeredBean, args) -> new ProdutoController(args.get(0)));
  }

  /**
   * Get the bean definition for 'produtoController'.
   */
  public static BeanDefinition getProdutoControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ProdutoController.class);
    beanDefinition.setInstanceSupplier(getProdutoControllerInstanceSupplier());
    return beanDefinition;
  }
}
