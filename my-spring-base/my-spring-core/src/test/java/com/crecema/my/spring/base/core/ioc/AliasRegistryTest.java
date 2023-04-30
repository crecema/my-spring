package com.crecema.my.spring.base.core.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.AliasRegistry;
import org.springframework.core.SimpleAliasRegistry;

/**
 * 别名注册表
 * AliasRegistry 的直接实现是 SimpleAliasRegistry，核心数据结构是 aliasMap，是一个 Map<String, String>，key 是别名，value 是 beanName
 * DefaultSingletonBeanRegistry 继承自 SimpleAliasRegistry，所以 DefaultSingletonBeanRegistry 也具有别名注册的功能
 */
public class AliasRegistryTest {

    @Test
    public void testSimpleAliasRegistry() {
        AliasRegistry aliasRegistry = new SimpleAliasRegistry();
        aliasRegistry.registerAlias("apiSpaceClient", "ApiSpaceClient");
        aliasRegistry.registerAlias("apiSpaceClient", "ApiSpaceCli");
        aliasRegistry.registerAlias("apiSpaceClient", "SpaceClient");
        Assertions.assertFalse(aliasRegistry.isAlias("apiSpaceClient"));
        Assertions.assertTrue(aliasRegistry.isAlias("ApiSpaceClient"));
        Assertions.assertEquals(3, aliasRegistry.getAliases("apiSpaceClient").length);
        Assertions.assertEquals(0, aliasRegistry.getAliases("ApiSpaceClient").length);
        aliasRegistry.removeAlias("ApiSpaceClient");
        aliasRegistry.removeAlias("ApiSpaceCli");
        aliasRegistry.removeAlias("SpaceClient");
    }

}
