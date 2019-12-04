package commons.boot.security;

import commons.boot.constant.RedissonClientBuilder;
import commons.boot.security.filter.StatelessFilter;
import commons.boot.security.realm.StatelessRealm;
import commons.boot.security.service.SecurityTokenService;
import commons.boot.security.subject.StatelessDefaultSubjectFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import javax.servlet.Filter;
import java.util.*;


public abstract class ShiroSecurityConfig {

    @Bean
    public abstract EnableShiroSecurity enableShiroSecurity();

    @Bean
    public ShiroFilterFactoryBean shiroFilter(EnableShiroSecurity enableShiroSecurity){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();

        shiroFilter.setSecurityManager(securityManager());
        Map<String, String> filterChainDefinitionMapping = shiroFilter.getFilterChainDefinitionMap();

        setUrl(filterChainDefinitionMapping, "anon", enableShiroSecurity.getAnonUrls() );
        setUrl(filterChainDefinitionMapping, "auth", enableShiroSecurity.getAuthUrls());

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("auth", new StatelessFilter(securityTokenService(enableShiroSecurity),enableShiroSecurity));
        shiroFilter.setFilters(filterMap);
        return shiroFilter;
    }

    @Bean
    public DefaultSessionManager defaultSessionManager() {
        DefaultSessionManager manager = new DefaultSessionManager();
        manager.setSessionValidationSchedulerEnabled(false);//禁用Session
        return manager;
    }

    @Bean("statelessRealm")
    public StatelessRealm statelessRealm() {
        return new StatelessRealm();
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(statelessRealm());//设置域对象
        DefaultSubjectDAO de = (DefaultSubjectDAO) manager.getSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = (DefaultSessionStorageEvaluator) de.getSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);//禁用Session存储
        StatelessDefaultSubjectFactory statelessDefaultSubjectFactory = new StatelessDefaultSubjectFactory();
        manager.setSubjectFactory(statelessDefaultSubjectFactory);
        manager.setSessionManager(defaultSessionManager());
        SecurityUtils.setSecurityManager(manager);
        return manager;
    }

    private void setUrl(Map<String, String> filterChainDefinitionMapping, String filterName, List<String> urlList) {
        if (!urlList.isEmpty()) {
            Iterator<String> iterator = urlList.iterator();
            while (iterator.hasNext()) {
                String url = iterator.next();
                filterChainDefinitionMapping.put(url, filterName);//
            }
        }
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor processor = new LifecycleBeanPostProcessor();
        return processor;
    }

//    开启注解
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
//        即使用cglib方式创建代理对象
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    @Bean
    public SecurityTokenService securityTokenService(EnableShiroSecurity enableShiroSecurity){
        SecurityTokenService securityTokenService = new SecurityTokenService(securityTokenRedisClient(enableShiroSecurity),enableShiroSecurity);
        return securityTokenService;
    }

    @Bean(name = "securityTokenRedisClient")
    public RedissonClient securityTokenRedisClient(EnableShiroSecurity enableShiroSecurity){
        String  redisHost = enableShiroSecurity.getRedisHost();
        Integer redisPort = enableShiroSecurity.getRedisPort();
        String redisPassword = enableShiroSecurity.getRedisPassword();
        Integer redisDataBase = enableShiroSecurity.getRedisDataBase();
        RedissonClient redissonClient = RedissonClientBuilder.redissonClient(redisHost, redisPort, redisPassword, redisDataBase);
        return redissonClient;
    }
}
