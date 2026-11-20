package com.AjudaAqui.trabalhoFinalDAW.controller;

import com.AjudaAqui.trabalhoFinalDAW.service.ClienteService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ClienteController}.
 */
@Generated
public class ClienteController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'clienteController'.
   */
  private static BeanInstanceSupplier<ClienteController> getClienteControllerInstanceSupplier() {
    return BeanInstanceSupplier.<ClienteController>forConstructor(ClienteService.class)
            .withGenerator((registeredBean, args) -> new ClienteController(args.get(0)));
  }

  /**
   * Get the bean definition for 'clienteController'.
   */
  public static BeanDefinition getClienteControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ClienteController.class);
    beanDefinition.setInstanceSupplier(getClienteControllerInstanceSupplier());
    return beanDefinition;
  }
}
