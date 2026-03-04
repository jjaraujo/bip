package br.com.bip.ejb.migration;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import org.flywaydb.core.Flyway;

@Startup
@Singleton
public class FlywayMigrationStartup {

    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) // em um cenario real eu teria criado um novo datasource para o flyway
    public void migrate() {
        DataSource ds = lookupDataSource();

        Flyway flyway = Flyway.configure()
                .dataSource(ds)
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
    }

    private DataSource lookupDataSource() {
        try {
            // aqui seria interessante colocar como variavel de config
            return (DataSource) new InitialContext().lookup("java:/jboss/datasources/BipDS");
        } catch (NamingException e) {
            throw new IllegalStateException("Não encontrou o DataSource no JNDI", e);
        }
    }
}