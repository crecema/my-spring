package com.crecema.my.spring.base.beans.factory;

import com.crecema.my.spring.base.ioc.config.PrintBeanPostProcessor;
import com.crecema.my.spring.base.ioc.config.TraceBeanPostProcessor;
import com.crecema.my.spring.base.ioc.domain.*;
import com.crecema.my.spring.base.ioc.domain.loop.A;
import com.crecema.my.spring.base.ioc.domain.loop.B;
import com.crecema.my.spring.base.ioc.domain.loop.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.AliasRegistry;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.beans.factory.support.AbstractBeanDefinition.AUTOWIRE_BY_NAME;

public class DefaultListableBeanFactoryTest {

    @Test // 作为别名注册表，管理别名，别名存储在 alisaMap 字段，由 SimpleAliasRegistry 实现
    public void testAsAlisaRegistry() {
        AliasRegistry alisaRegistry = new DefaultListableBeanFactory();
        alisaRegistry.registerAlias("mahaoran", "haoran");
        assertTrue(alisaRegistry.isAlias("haoran"));
        assertEquals("haoran", alisaRegistry.getAliases("mahaoran")[0]);
        alisaRegistry.removeAlias("haoran");
    }

    @Test // 作为单例对象注册表，管理单例对象，存储在一级缓存中，由 DefaultSingletonBeanRegistry 实现
    public void testAsSingletonBeanRegistry() {
        SingletonBeanRegistry singletonBeanRegistry = new DefaultSingletonBeanRegistry();

        // as SingletonBeanRegistry
        UserRepository userRepository = new UserRepository();
        singletonBeanRegistry.registerSingleton("userRepository", userRepository);
        assertTrue(singletonBeanRegistry.containsSingleton("userRepository"));
        assertNotNull(singletonBeanRegistry.getSingleton("userRepository"));
        assertEquals("userRepository", singletonBeanRegistry.getSingletonNames()[0]);
        assertEquals(1, singletonBeanRegistry.getSingletonCount());

        // as FactoryBeanRegistry，对于FactoryBean,一级缓存中存的是FactoryBean<?>,
        // FactoryBean生产的实际实例存在factoryBeanObjectCache中，由FactoryBeanRegistrySupport实现
        UserRepositoryFactoryBean factoryBean = new UserRepositoryFactoryBean();
    }

    @Test // 作为bean定义注册表，管理bean定义，存储在 beanDefinitionMap 字段，由 DefaultListableBeanFactory 直接实现
    public void testAsBeanDefinitionRegistry() {
        BeanDefinitionRegistry beanDefinitionRegistry = new DefaultListableBeanFactory();
        beanDefinitionRegistry.registerBeanDefinition("userRepository", newBeanDefinition(UserRepository.class));
        assertTrue(beanDefinitionRegistry.containsBeanDefinition("userRepository"));
        assertNotNull(beanDefinitionRegistry.getBeanDefinition("userRepository"));
        assertEquals("userRepository", beanDefinitionRegistry.getBeanDefinitionNames()[0]);
        assertEquals(1, beanDefinitionRegistry.getBeanDefinitionCount());
        assertTrue(beanDefinitionRegistry.isBeanNameInUse("userRepository"));
        beanDefinitionRegistry.removeBeanDefinition("userRepository");
    }

    @Test // 作为BeanFactory，对外暴露api，给调用者提供 Bean 实例
    public void testAsBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        GenericBeanDefinition userRepositoryDefinition = new GenericBeanDefinition();
        userRepositoryDefinition.setBeanClass(UserRepository.class);
        beanFactory.registerBeanDefinition("userRepository", userRepositoryDefinition);

        GenericBeanDefinition userServiceDefinition = new GenericBeanDefinition();
        userServiceDefinition.setBeanClass(UserServiceImpl.class);
        beanFactory.registerBeanDefinition("userServiceImpl", userServiceDefinition);
        beanFactory.registerAlias("userServiceImpl", "userService");

        GenericBeanDefinition userControllerDefinition = new GenericBeanDefinition();
        userControllerDefinition.setBeanClass(UserController.class);
        beanFactory.registerBeanDefinition("userController", userControllerDefinition);

        User user = new User(1000, "haoran", Sex.MALE, 24);
        beanFactory.registerSingleton("user", user);

        UserRepository userRepository;
        userRepository = (UserRepository) beanFactory.getBean("userRepository");
        assertNotNull(userRepository);
        userRepository = beanFactory.getBean(UserRepository.class);
        assertNotNull(userRepository);
        userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        assertNotNull(userRepository);

        UserService userService;
        userService = beanFactory.getBean(UserService.class);
        assertNotNull(userService);
        assertNull(((UserServiceImpl) userService).getUserRepository()); // 没有自动注入

        UserController userController;
        userController = beanFactory.getBean(UserController.class, userService);
        assertNotNull(userController);
    }

    @Test // BeanFactory扩展，可以对外暴露所有被管理的bean定义，让调用者知道管理了那些Bean
    public void testAsListableBeanFactory() {
        DefaultListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();

        listableBeanFactory.registerBeanDefinition("userRepository", newBeanDefinition(UserRepository.class));

        listableBeanFactory.registerBeanDefinition("userServiceImpl", newBeanDefinition(UserServiceImpl.class));
        listableBeanFactory.registerAlias("userServiceImpl", "userService");

        listableBeanFactory.registerBeanDefinition("userController", newBeanDefinition(UserController.class));

        User user = new User(1000, "haoran", Sex.MALE, 24);
        listableBeanFactory.registerSingleton("user", user);

        assertTrue(listableBeanFactory.containsBeanDefinition("userRepository"));
        assertEquals(3, listableBeanFactory.getBeanDefinitionCount());
        String[] beanDefinitionNames = listableBeanFactory.getBeanDefinitionNames();
        assertEquals(3, beanDefinitionNames.length);
        String[] beanNamesForType = listableBeanFactory.getBeanNamesForType(UserService.class);
        assertEquals(1, beanNamesForType.length); // 不包含别名

    }

    @Test // BeanFactory扩展，提供访问父级的能力
    public void testAsHierarchicalBeanFactory() {
        DefaultListableBeanFactory fatherBeanFactory = new DefaultListableBeanFactory();
        GenericBeanDefinition userRepositoryDefinition = new GenericBeanDefinition();
        userRepositoryDefinition.setBeanClass(UserRepository.class);
        fatherBeanFactory.registerBeanDefinition("userRepository", userRepositoryDefinition);

        DefaultListableBeanFactory sonBeanFactory = new DefaultListableBeanFactory();
        sonBeanFactory.setParentBeanFactory(fatherBeanFactory);

        assertNotNull(sonBeanFactory.getBean(UserRepository.class));
    }

    @Test // BeanFactory扩展，提供配置BeanFactory的能力，比如父级工厂，BeanPostProcessor，自定义生命周期，ClassLoader等
    public void testAsConfigurableBeanFactory() {
        DefaultListableBeanFactory configurableBeanFactory = new DefaultListableBeanFactory();
        configurableBeanFactory.registerBeanDefinition("userRepository", newBeanDefinition(UserRepository.class));
        ClassLoader beanClassLoader = configurableBeanFactory.getBeanClassLoader();
        BeanExpressionResolver beanExpressionResolver = configurableBeanFactory.getBeanExpressionResolver();
        configurableBeanFactory.addBeanPostProcessor(new PrintBeanPostProcessor());
        configurableBeanFactory.addBeanPostProcessor(new TraceBeanPostProcessor());
        UserRepository userRepository = configurableBeanFactory.getBean(UserRepository.class);
        assertNotNull(userRepository);
        assertEquals(2, configurableBeanFactory.getBeanPostProcessorCount());
    }

    @Test // BeanFactory扩展，提供自动注入能力
    public void testAsAutowireCapableBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        GenericBeanDefinition userRepositoryDefinition = new GenericBeanDefinition();
        userRepositoryDefinition.setBeanClass(UserRepository.class);
        userRepositoryDefinition.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_NAME);
        beanFactory.registerBeanDefinition("userRepository", userRepositoryDefinition);

        GenericBeanDefinition userServiceDefinition = new GenericBeanDefinition();
        userServiceDefinition.setBeanClass(UserServiceImpl.class);
        userServiceDefinition.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_NAME);
        beanFactory.registerBeanDefinition("userService", userServiceDefinition);

        GenericBeanDefinition userControllerDefinition = new GenericBeanDefinition();
        userControllerDefinition.setBeanClass(UserController.class);
        userControllerDefinition.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR);
        beanFactory.registerBeanDefinition("userController", userControllerDefinition);

        UserController userController = beanFactory.getBean("userController", UserController.class);
        assertNotNull(userController.user(1001));

        UserServiceImpl userService = (UserServiceImpl) beanFactory.getBean("userService");
        assertNotNull(userService.getUserRepository());
    }

    @Test // BeanFactory扩展，提供了分析和修改 Bean 定义以及预实例化单例的能力
    public void testAsConfigurableListableBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userRepository", newBeanDefinition(UserRepository.class));
        beanFactory.registerBeanDefinition("userService", newBeanDefinition(UserServiceImpl.class));

        // 预实例化单例bean
        beanFactory.preInstantiateSingletons();
    }

    @Test // 循环依赖
    public void testLoopDepend() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        GenericBeanDefinition aDefinition = new GenericBeanDefinition();
        GenericBeanDefinition bDefinition = new GenericBeanDefinition();
        GenericBeanDefinition cDefinition = new GenericBeanDefinition();
        aDefinition.setBeanClass(A.class);
        bDefinition.setBeanClass(B.class);
        cDefinition.setBeanClass(C.class);
        aDefinition.setAutowireMode(AUTOWIRE_BY_NAME);
        bDefinition.setAutowireMode(AUTOWIRE_BY_NAME);
        cDefinition.setAutowireMode(AUTOWIRE_BY_NAME);
        beanFactory.registerBeanDefinition("a", aDefinition);
        beanFactory.registerBeanDefinition("b", bDefinition);
        beanFactory.registerBeanDefinition("c", cDefinition);

        Object a = beanFactory.getBean("a");
        Object b = beanFactory.getBean("b");
        Object c = beanFactory.getBean("c");

        System.out.println("success");
    }

    private BeanDefinition newBeanDefinition(Class<?> beanClass) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        return beanDefinition;
    }

}
