package com.hzz.empty;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author: hezz
 */
public class EmptyImplScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {
    private String beanName;
    private ApplicationContext applicationContext;
    private String basePackages;

    @Override
    public void setBeanName(String name) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathScanner cps = new ClassPathScanner(registry);
        cps.registerFilters();
        cps.scan(basePackages);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setBasePackages(String basePackages) {
        this.basePackages = basePackages;
    }

    static class ClassPathScanner extends ClassPathBeanDefinitionScanner {
        private EmptyImplFactoryBean<?> factoryBean = new EmptyImplFactoryBean<Object>();

        public ClassPathScanner(BeanDefinitionRegistry registry) {
            super(registry, false);
        }

        @Override
        protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
            if (!beanDefinitions.isEmpty()) {
                processBeanDefinitions(beanDefinitions);
            }
            return beanDefinitions;
        }

        protected void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
            GenericBeanDefinition definition;
            for (BeanDefinitionHolder holder : beanDefinitions) {
                definition = (GenericBeanDefinition) holder.getBeanDefinition();
                definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());
                definition.setBeanClass(this.factoryBean.getClass());
            }
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
        }

        public void registerFilters() {
            addIncludeFilter(new TypeFilter() {
                @Override
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                    return true;
                }
            });
        }
    }
}
