package com.AjudaAqui.trabalhoFinalDAW.service;

import com.AjudaAqui.trabalhoFinalDAW.repository.ItemRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ItemService}.
 */
@Generated
public class ItemService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'itemService'.
   */
  private static BeanInstanceSupplier<ItemService> getItemServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ItemService>forConstructor(ItemRepository.class)
            .withGenerator((registeredBean, args) -> new ItemService(args.get(0)));
  }

  /**
   * Get the bean definition for 'itemService'.
   */
  public static BeanDefinition getItemServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ItemService.class);
    beanDefinition.setInstanceSupplier(getItemServiceInstanceSupplier());
    return beanDefinition;
  }
}
