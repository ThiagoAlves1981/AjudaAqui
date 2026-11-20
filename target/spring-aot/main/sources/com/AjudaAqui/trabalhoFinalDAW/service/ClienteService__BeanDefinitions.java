package com.AjudaAqui.trabalhoFinalDAW.service;

import com.AjudaAqui.trabalhoFinalDAW.repository.ClienteRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ClienteService}.
 */
@Generated
public class ClienteService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'clienteService'.
   */
  private static BeanInstanceSupplier<ClienteService> getClienteServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ClienteService>forConstructor(ClienteRepository.class)
            .withGenerator((registeredBean, args) -> new ClienteService(args.get(0)));
  }

  /**
   * Get the bean definition for 'clienteService'.
   */
  public static BeanDefinition getClienteServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ClienteService.class);
    beanDefinition.setInstanceSupplier(getClienteServiceInstanceSupplier());
    return beanDefinition;
  }
}
