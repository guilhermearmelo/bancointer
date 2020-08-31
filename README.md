# API REST Banco Inter

O projeto foi construído usando o gerenciador da pacotes maven, basta importar o projeto como um 'maven project'.
As configurações de conexão com o Banco de Dados estão no arquivo: /resources/application.properties
O Banco de Dados utilizado foi o PostgreSQL com o nome do Banco de Dados: 'inter'

## Calculo do dígito único

O digito unico é calculado dentro de uma função que recebe (String n, int k). Um vetor de 10 posições armazena quantas vezes o numero i (0-9) foi encontrado na String n. Posteriormente esse número é multiplicado por k, e assim somado para gerar uma chamada recursiva com o resultado obtido sem repetições. Esse padrão foi adotado devido ao número de chamadas recursivas que o Java pode suportar, tendo em vista o tamanho das possíveis entradas.

## Cache

A criação da cache permite buscar as n informações mais recentes de dígitos únicos calculados, sendo n o tamanho da cache. Foi utilizada uma política de fila para garantir que as informações ficassem pelo menos n vezes disponíveis para consulta.

## CRUD de Usuários

Pode ser acessado tanto na documentação, pelo /Swagger.html , quanto pela coleção disponibiliza do Postman.

## Testes Unitários

Foram feitos testes unitários utilizando JUnit para verificar se as entradas para calculo são bem tratadas, bem como se o método de cálculo é eficaz.
Os testes podem ser realizados preferencialmente com JUnit 4.

## Testes de Integração

Disponibilizados testes unitários dos endpoints de CRUD de Usuários e calculos de dígitos únicos na coleção: postman_collection.json
