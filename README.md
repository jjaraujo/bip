# Tecnologias usadas no desenvolvimento

Java 17

Maven 3.8.5+

Wildfly-39.0.1.Final

Sql Server 2025 (17.0)

Node v20.19.0


# Instalação WildFly

Baixar Java, Maven e SQL Server e configurar as variáveis de ambiente 

Baixar a versão 39.0.1.Final do wildFly https://github.com/wildfly/wildfly/releases/download/39.0.1.Final/wildfly-39.0.1.Final.zip. Descompactar no diretório C:/ (sugestão)

Na raiz do projeto abrir terminal e executar:

```bash
mvn clean package
```
Após construir, criar o seguinte diretório, caso não esteja criado:
```
C:\wildfly-39.0.1.Final\modules\system\layers\base\com\microsoft\sqlserver\main
```
Criar o arquivo module.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.9" name="com.microsoft.sqlserver">
  <resources>
    <resource-root path="com.microsoft.sqlserver-mssql-jdbc-13.2.1.jre11.jar"/>
  </resources>
  <dependencies>
    <module name="java.sql"/>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
```
Copiar para essa pasta o drive .jar do sqlServer baixado no projeto:
```
[RAIZ_PROJETO]\ear-module\target\projetojoao\lib\com.microsoft.sqlserver-mssql-jdbc-13.2.1.jre11.jar
```
Esse foi  jar usado no desenvolvimento e está configurado no pom do ear-module. Caso o banco usado ou a versão do sqlServer seja outra, devem ser feitas alterações no module.xml, no POM e colocar o drive também aqui.


Após copiar, abrir um terminal:
```bash
cd C:\wildfly-39.0.1.Final\bin\
standalone.bat
```
Abrir novo terminal e executar 
```bash
cd C:\wildfly-39.0.1.Final\bin\
jboss-cli.bat --connect
)
```
Em caso de sucesso, deve aparecer:
```bash
[standalone@localhost:9990 /]
````
Executar (alterar de acordo com o banco usado):
```bash
/subsystem=datasources/jdbc-driver=sqlserver:add( 
 driver-name=sqlserver, 
 driver-module-name=com.microsoft.sqlserver, 
 driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver, 
 driver-xa-datasource-class-name=com.microsoft.sqlserver.jdbc.SQLServerXADataSource 
)
```
Resultado deve ser
```
{"outcome" => "success"}
```
Na sequencia execute (Atentar-se ao drivename e a string de conexão para ser compativel ao banco usado)
```
data-source add 
 --name=BipDS 
 --jndi-name=java:/jboss/datasources/BipDS 
 --driver-name=sqlserver 
 --connection-url=jdbc:sqlserver://localhost:52354;databaseName=master;encrypt=true;trustServerCertificate=true 
 --user-name=user1 
 --password=12345qwert 
 --min-pool-size=5 
 --max-pool-size=20 
 --enabled=true
```

Tendo sucesso, o Wildfly estará configurado para ser utilizado.



Swagger
```
http://localhost:8080/api/swagger-ui/index.html#/
```



# Instalação Angular
Instalar node v20.19.0

```
npm install
npm start
```