# Java Verse Bank
Este documento tem como objetivo detalhar, por meio da arquitetura de sistemas, requisitos, diagrama de classe e limitações e considerações relacionadas ao código JavaVerse Bank. Trata-se de um pequeno sistema de CRUD que simula algumas etapas do sistema bancário, como criação, edição, atualização, remoção e listagem de clientes, contas correntes e contas poupança. Estas últimas também possuem métodos para saque e depósito. O programa foi desenvolvido para atender a necessidades específicas e fornecer funcionalidades de acordo com o escopo definido para este projeto. A seguir, apresentamos uma descrição abrangente do software, juntamente com as informações relevantes.


## Arquitetura de Sistemas
A arquitetura do sistema foi organizada levando em consideração a divisão em camadas:  _DAO_  (_Data Access Object_),  _Domain_  (ou modelo) e  _Service_. Sua implementação foi feita utilizando a linguagem de programação orientada a objetos, Java.
-   **Camada DAO**  : Essa camada é responsável por mapear e realizar o acesso aos dados, orientando a execução das consultas no banco de dados e encapsulando a persistência dos dados. Ela fornece métodos para inserir, atualizar, remover e recuperar informações;
-   **Camada Domain**  : Nessa camada, temos as entidades do sistema, com atributos e métodos diretamente relacionados à lógica de negócio. As entidades presentes nesse sistema são: clientes, conta, conta corrente e conta poupança; e
-   **Camada Service**  : Aqui está concentrada toda a coordenação das entidades e seu relacionamento com a lógica de negócio do sistema. Essa camada fornece funções que permitem a manipulação e interação da classe Main.java com a camada de encapsulamento dos dados (DAO).

Em síntese, o principal objetivo da adoção dessa arquitetura foi separar as responsabilidades do sistema, o que proporciona uma série de vantagens, como facilidade de manutenção, legibilidade, extensibilidade, entre outras possíveis.

#### Diagrama de fluxo da informação na arquitetura
![Diagrama de fluxo da informação na arquitetura.](https://github.com/AngeloSouzaOliveira/Banck-CRUD/blob/2f3c17de08e2dfeb4e067f4b93884fd268ad7b35/images_doc/fluxo.png)



## Requisitos do Sistema
### - Requisitos funcionais
| |                                                                                                               |
|------|------------------------------------------------------------------------------------------------------------------------------------------------|
| RF1  | Criar um cliente com nome e CPF. 
| RF2  | Ler os detalhes de um cliente existente por meio do seu CPF.                                                                                   |
| RF3  | Atualizar o nome de um cliente existente.                                                                                                      |
| RF4  | Excluir um cliente existente com base no seu CPF.                                                                                              |
| RF5  | Criar uma conta com número, CPF do cliente associado e saldo.                                                                                  |
| RF6  | Ler os detalhes de uma conta existente por meio do seu número.                                                                                 |
| RF7  | Atualizar o saldo e cheque especial de uma conta existente.                                                                                    |
| RF8  | Excluir uma conta existente com base no seu número.                                                                                            |
| RF9  | Criar uma conta corrente, herdeira da classe Conta, com limite de cheque especial.                                                             |
| RF10 | Ler os detalhes de uma conta corrente existente, incluindo o limite de cheque especial.                                                        |
| RF11 | Atualizar o limite de cheque especial de uma conta corrente existente.                                                                         |
| RF12 | Criar uma conta poupança, herdeira da classe Conta, com taxa de juros.                                                                         |
| RF13 | Ler os detalhes de uma conta poupança existente, incluindo a taxa de juros.                                                                    |
| RF14 | Permitir que o usuário realize um saque de determinado valor em uma conta corrente ou poupança.                                                |
| RF15 | Verificar se o saldo disponível na conta é suficiente para o saque solicitado.                                                                 |
| RF16 | Atualizar o saldo da conta após a conclusão bem-sucedida de um saque.                                                                          |
| RF17 | Permitir que o usuário faça um depósito de determinado valor em uma conta corrente ou poupança.                                                |
| RF18 | Adicionar o valor depositado ao saldo atual da conta.                                                                                          |
| RF19 | Permitir que o cliente realize saques na Conta Corrente, mesmo que o valor seja superior ao saldo disponível, até o limite do cheque especial. |
| RF20 | Verificar se o valor do saque solicitado não ultrapassa a soma do saldo disponível e o limite do cheque especial.                              |
| RF21 | Atualizar o saldo da conta corrente após a conclusão bem-sucedida de um saque no cheque especial.                                              |
| RF22 | Ao realizar um depósito em uma conta poupança, incrementar a taxa de juros atual em 0,005%.       
                                                                

    
      

### - Requisitos não funcionais
| |                                                                                                                            |
|-------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| RNF1  | Todas as operações realizadas devem ser salvas no banco de dados.   
| RNF2  | O sistema deve ser capaz de recuperar as informações corretas das operações registradas no banco de dados.                                                                                   |
| RNF3  | Todas as operações devem ser realizadas através do console.                                                                                                                                  |
| RNF4  | A interface do console deve ser intuitiva e de fácil utilização.                                                                                                                             |
| RNF5  | O sistema deve fornecer feedback claro e adequado ao usuário durante as operações.                                                                                                           |
| RNF6  | O código-fonte deve ser organizado e estruturado de acordo com os princípios de abstração, polimorfismo, herança e encapsulamento.                                                           |
| RNF7  | O uso adequado dos conceitos de orientação a objetos deve garantir um código mais modular, reutilizável e de fácil manutenção.                                                               |
| RNF8  | O código Java deve seguir as boas práticas de endentação e organização, de acordo com as convenções estabelecidas pela comunidade Java.                                                      |
| RNF9  | A estrutura de pacotes e classes deve ser bem-organizada, facilitando a compreensão e manutenção do código.                                                                                  |
| RNF10 | O código deve utilizar as tratativas adequadas de exceções usando blocos try/catch para capturar e lidar com erros e exceções que possam ocorrer durante a execução.                         |
| RNF11 | As exceções devem ser tratadas de forma apropriada, fornecendo mensagens de erro informativas e garantindo que o sistema continue funcionando corretamente após a ocorrência de uma exceção. |
| RNF12 | O sistema deve ser capaz de se conectar e interagir com o banco de dados PostgreSQL usando o driver JDBC.                                                                                    |


## Diagrama de Classe
O presente diagrama de classe inclui as seguintes classes: Cliente, Conta,Conta Corrente e Conta Poupança. A classe Cliente possui atributos nome e CPF e com modificadores de acessos privados. A classe abstrata Conta tem atributos protegidos que são número, CPF e saldo. Já a classe Conta Corrente e Conta Poupança herdam Conta possuem atributos privados de exclusivo como cheque especial (Conta Corrente) e taxa de juros (Conta Poupança).

![Diagrama de classe do sistema](https://github.com/AngeloSouzaOliveira/Banck-CRUD/blob/2f3c17de08e2dfeb4e067f4b93884fd268ad7b35/images_doc/diagrama_classe.png)

## Uso do Software
O presente programa possibilita ao usuário interagir com diferentes opções de um menu que permite executar ações intrinsecamente relacionadas às entidades Cliente, Conta Corrente e Conta Poupança.

A entrada de dados é feita por meio do console, utilizando a classe Scanner, que pode ser coordenada por três serviços:  _ClienteService, ContaCorrenteService_  e  _ContaPoupancaService._  Cada um desses serviços é responsável por executar operações para suas respectivas entidades: Cliente, Conta Corrente e Conta Poupança.

Assim que o programa é iniciado, temos o menu principal dentro de um loop que é executado até que a opção 4 seja selecionada, o que provoca a saída do loop e consequentemente do programa. Caso a opção 4 não seja selecionada, as opções 1, 2 e 3 estão relacionadas, respectivamente, com submenus de cada uma dessas entidades: clientes, contas correntes e contas poupança. Cada submenu tem seu próprio loop interno, com opções para voltar à etapa anterior ou chamar os métodos dos serviços mencionados anteriormente para executar uma ação.

Vale destacar que alguns métodos auxiliares para exibir o menu, bem como alguns métodos nativos do Java, como  _replaceAll_  e tratamentos de exceções, foram implementados na classe  _Main_, a fim de mitigar a complexidade e realizar tratamentos adequados.


## Limitações, Problemas e Segurança
 1. O sistema não abarca todas as funcionalidades de um sistema bancário real;
 2. Não foi implementado no sistema a possibilidade de transferência de recursos entre contas;
 3. O sistema não foi testado para lidar com um grande volume de dados e operações de forma simultânea;
 4. O sistema não implementa técnicas de criptografia, autenticação,
    autorização e gerenciamento de usuários; e
 5. O sistema expõe algumas informações sensíveis.

## Classes 
### - Pacote Domain  
![Pacote Domain](https://github.com/AngeloSouzaOliveira/Banck-CRUD/blob/2f3c17de08e2dfeb4e067f4b93884fd268ad7b35/images_doc/packageDomain.png)

### - Pacote DAO
![Pacote Dao](https://github.com/AngeloSouzaOliveira/Banck-CRUD/blob/2f3c17de08e2dfeb4e067f4b93884fd268ad7b35/images_doc/packageDao.png)

### - Pacote Service
![Pacote Service](https://github.com/AngeloSouzaOliveira/Banck-CRUD/blob/2f3c17de08e2dfeb4e067f4b93884fd268ad7b35/images_doc/packageService.png)
