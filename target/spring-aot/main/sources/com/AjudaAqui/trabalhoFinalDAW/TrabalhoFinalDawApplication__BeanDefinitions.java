package com.AjudaAqui.trabalhoFinalDAW;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TrabalhoFinalDawApplication}.
 */
@Generated
public class TrabalhoFinalDawApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'trabalhoFinalDawApplication'.
   */
  public static BeanDefinition getTrabalhoFinalDawApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TrabalhoFinalDawApplication.class);
    beanDefinition.setInstanceSupplier(TrabalhoFinalDawApplication::new);
    return beanDefinition;
  }
}
