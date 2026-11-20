package com.AjudaAqui.trabalhoFinalDAW.service;

import com.AjudaAqui.trabalhoFinalDAW.repository.PedidoRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link PedidoService}.
 */
@Generated
public class PedidoService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'pedidoService'.
   */
  private static BeanInstanceSupplier<PedidoService> getPedidoServiceInstanceSupplier() {
    return BeanInstanceSupplier.<PedidoService>forConstructor(PedidoRepository.class)
            .withGenerator((registeredBean, args) -> new PedidoService(args.get(0)));
  }

  /**
   * Get the bean definition for 'pedidoService'.
   */
  public static BeanDefinition getPedidoServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PedidoService.class);
    beanDefinition.setInstanceSupplier(getPedidoServiceInstanceSupplier());
    return beanDefinition;
  }
}
