package com.AjudaAqui.trabalhoFinalDAW.controller;

import com.AjudaAqui.trabalhoFinalDAW.service.ItemService;
import com.AjudaAqui.trabalhoFinalDAW.service.PedidoService;
import com.AjudaAqui.trabalhoFinalDAW.service.ProdutoService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ItemController}.
 */
@Generated
public class ItemController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'itemController'.
   */
  private static BeanInstanceSupplier<ItemController> getItemControllerInstanceSupplier() {
    return BeanInstanceSupplier.<ItemController>forConstructor(ItemService.class, PedidoService.class, ProdutoService.class)
            .withGenerator((registeredBean, args) -> new ItemController(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'itemController'.
   */
  public static BeanDefinition getItemControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ItemController.class);
    beanDefinition.setInstanceSupplier(getItemControllerInstanceSupplier());
    return beanDefinition;
  }
}
