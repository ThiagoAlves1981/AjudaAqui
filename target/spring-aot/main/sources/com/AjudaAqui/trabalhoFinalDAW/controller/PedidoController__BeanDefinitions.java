package com.AjudaAqui.trabalhoFinalDAW.controller;

import com.AjudaAqui.trabalhoFinalDAW.service.ClienteService;
import com.AjudaAqui.trabalhoFinalDAW.service.PedidoService;
import com.AjudaAqui.trabalhoFinalDAW.service.ProdutoService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link PedidoController}.
 */
@Generated
public class PedidoController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'pedidoController'.
   */
  private static BeanInstanceSupplier<PedidoController> getPedidoControllerInstanceSupplier() {
    return BeanInstanceSupplier.<PedidoController>forConstructor(PedidoService.class, ClienteService.class, ProdutoService.class)
            .withGenerator((registeredBean, args) -> new PedidoController(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'pedidoController'.
   */
  public static BeanDefinition getPedidoControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PedidoController.class);
    beanDefinition.setInstanceSupplier(getPedidoControllerInstanceSupplier());
    return beanDefinition;
  }
}
