package br.com.bip.backend.config;

import br.com.bip.domain.port.IBeneficioEjbService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class EjbConfig {

    @Bean
    public IBeneficioEjbService beneficioService() throws Exception {

        JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
        // joguei o string com nome de jndi assim direto, mas precisaria ajustar para pegar a version-name do pom
        jndi.setJndiName(
                "java:global/projetojoao/br.com.bip-ejb-module-1.0.0/BeneficioEjbService!br.com.bip.domain.port.IBeneficioEjbService"
        );

        jndi.afterPropertiesSet();

        return (IBeneficioEjbService) jndi.getObject();
    }
}