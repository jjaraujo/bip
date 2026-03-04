\# Tecnologias usadas no desenvolvimento



Java 17



Maven 3.8.5+



Wildfly-39.0.1.Final



Sql Server 2025 (17.0)



\# Instalação



Baixar Java, Maven e SQL Server e configurar as variáveis de ambiente 



Baixar a versão 39.0.1.Final do wildFly https://github.com/wildfly/wildfly/releases/download/39.0.1.Final/wildfly-39.0.1.Final.zip. Descompactar no diretório C:/ (sugestão)



Na raiz do projeto abrir terminal e executar:



```bash

mvn clean package

```

Após construir, criar o seguinte diretório, caso não esteja criado:

```

C:\\wildfly-39.0.1.Final\\modules\\system\\layers\\base\\com\\microsoft\\sqlserver\\main

```

Criar o arquivo module.xml:

```xml

<?xml version="1.0" encoding="UTF-8"?>

<module xmlns="urn:jboss:module:1.9" name="com.microsoft.sqlserver">

&nbsp; <resources>

&nbsp;   <resource-root path="com.microsoft.sqlserver-mssql-jdbc-13.2.1.jre11.jar"/>

&nbsp; </resources>

&nbsp; <dependencies>

&nbsp;   <module name="java.sql"/>

&nbsp;   <module name="javax.api"/>

&nbsp;   <module name="javax.transaction.api"/>

&nbsp; </dependencies>

</module>

```

Copiar para essa pasta o drive .jar do sqlServer baixado no projeto:

```

\[RAIZ\_PROJETO]\\ear-module\\target\\projetojoao\\lib\\com.microsoft.sqlserver-mssql-jdbc-13.2.1.jre11.jar

```

Esse foi  jar usado no desenvolvimento e está configurado no pom do ear-module. Caso o banco usado ou a versão do sqlServer seja outra, devem ser feitas alterações no module.xml, no POM e colocar o drive também aqui.





Após copiar, abrir um terminal:

```bash

cd C:\\wildfly-39.0.1.Final\\bin\\

standalone.bat

```

Abrir novo terminal e executar 

```bash

cd C:\\wildfly-39.0.1.Final\\bin\\

jboss-cli.bat --connect

)

```

Em caso de sucesso, deve aparecer:

```bash

\[standalone@localhost:9990 /]

````

Executar:

```bash

/subsystem=datasources/jdbc-driver=sqlserver:add( ^

&nbsp;driver-name=sqlserver, ^

&nbsp;driver-module-name=com.microsoft.sqlserver, ^

&nbsp;driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver, ^

&nbsp;driver-xa-datasource-class-name=com.microsoft.sqlserver.jdbc.SQLServerXADataSource ^

)

```

\## Usage



```python

import foobar



\# returns 'words'

foobar.pluralize('word')



\# returns 'geese'

foobar.pluralize('goose')



\# returns 'phenomenon'

foobar.singularize('phenomena')

```



\## Contributing



Pull requests are welcome. For major changes, please open an issue first

to discuss what you would like to change.



Please make sure to update tests as appropriate.



\## License



\[MIT](https://choosealicense.com/licenses/mit/)# Tecnologias usadas no desenvolvimento



Java 17



Maven 3.8.5+



Wildfly-39.0.1.Final



Sql Server 2025 (17.0)



\# Instalação



Baixar Java, Maven e SQL Server e configurar as variáveis de ambiente 



Baixar a versão 39.0.1.Final do wildFly https://github.com/wildfly/wildfly/releases/download/39.0.1.Final/wildfly-39.0.1.Final.zip. Descompactar no diretório C:/ (sugestão)



Na raiz do projeto abrir terminal e executar:



```bash

mvn clean package

```

Após construir, criar o seguinte diretório, caso não esteja criado:

```

C:\\wildfly-39.0.1.Final\\modules\\system\\layers\\base\\com\\microsoft\\sqlserver\\main

```

Criar o arquivo module.xml:

```xml

<?xml version="1.0" encoding="UTF-8"?>

<module xmlns="urn:jboss:module:1.9" name="com.microsoft.sqlserver">

&nbsp; <resources>

&nbsp;   <resource-root path="com.microsoft.sqlserver-mssql-jdbc-13.2.1.jre11.jar"/>

&nbsp; </resources>

&nbsp; <dependencies>

&nbsp;   <module name="java.sql"/>

&nbsp;   <module name="javax.api"/>

&nbsp;   <module name="javax.transaction.api"/>

&nbsp; </dependencies>

</module>

```

Copiar para essa pasta o drive .jar do sqlServer baixado no projeto:

```

\[RAIZ\_PROJETO]\\ear-module\\target\\projetojoao\\lib\\com.microsoft.sqlserver-mssql-jdbc-13.2.1.jre11.jar

```

Esse foi  jar usado no desenvolvimento e está configurado no pom do ear-module. Caso o banco usado ou a versão do sqlServer seja outra, devem ser feitas alterações no module.xml, no POM e colocar o drive também aqui.





Após copiar, abrir um terminal:

```bash

cd C:\\wildfly-39.0.1.Final\\bin\\

standalone.bat

```

Abrir novo terminal e executar 

```bash

cd C:\\wildfly-39.0.1.Final\\bin\\

jboss-cli.bat --connect

)

```

Em caso de sucesso, deve aparecer:

```bash

\[standalone@localhost:9990 /]

````

Executar:

```bash

/subsystem=datasources/jdbc-driver=sqlserver:add( ^

&nbsp;driver-name=sqlserver, ^

&nbsp;driver-module-name=com.microsoft.sqlserver, ^

&nbsp;driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver, ^

&nbsp;driver-xa-datasource-class-name=com.microsoft.sqlserver.jdbc.SQLServerXADataSource ^

)

```

\## Usage



```python

import foobar



\# returns 'words'

foobar.pluralize('word')



\# returns 'geese'

foobar.pluralize('goose')



\# returns 'phenomenon'

foobar.singularize('phenomena')

```



\## Contributing



Pull requests are welcome. For major changes, please open an issue first

to discuss what you would like to change.



Please make sure to update tests as appropriate.



\## License



\[MIT](https://choosealicense.com/licenses/mit/)

