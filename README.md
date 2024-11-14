# AluMind API 🤖

| Autor | Alisso Jaques |
| -------------  | --- |
| Tecnologias | Java, Framework Spring, JPA, Hibernate, Rest, SQL, MySQL, DDD, OpenAI, Inteligência Artificial Generativa, LLM's

[Link para o projeto](https://github.com/alissonjaques/alumind-api)

## Descrição 

<p align="justify">A AluMind é uma startup que oferece um aplicativo focado em bem-estar e saúde mental, proporcionando aos usuários acesso a meditações guiadas, sessões de terapia, e conteúdos educativos sobre saúde mental. Com o alto crescimento da base de usuários, a AluMind está começando a ter gargalos para analisar feedbacks vindo dos usuários em diferentes plataformas (canais de atendimento ao cliente; comunidades no Discord; redes sociais). Portanto, a AluMind API tem como objetivo resolver esses problemas, ela analisa os feedbacks vindos dos usuários, classifica-os a partir do seu sentimento e elenca as possíveis melhorias contidas neles.</p>


## Como executar a aplicação 

- Clone o projeto em sua máquina; 
- Abra o projeto no IntelliJ;
- No painel lateral da IDE, localize o arquivo pom.xml;
- Clique na opção de atualização (ícone de recarregar no menu lateral direito da IDE) para baixar e instalar todas as dependências;
- Configure as seguintes variáveis de ambiente no seu computador ou servidor:

       ALUMIND_API_KEY = <sua chave de acesso criada na conta da OpenAI>
       ALUMIND_DATASOURCE_USERNAME = <seu usuário do MySQL>
       ALUMIND_DATASOURCE_PASSWORD = <sua senha do MySQL>
       ALUMIND_DATASOURCE_URL = jdbc:mysql://localhost/alumind_api?createDatabaseIfNotExist=true
- Execute a aplicação. Na primeira execução, o sistema irá rodar as migrações para configurar o banco de dados automaticamente.
- A aplicação ficará escutando na porta 8080 e requisições deve ser feitas, por exemplo, assim: http://localhost:8080/feedbacks

Aproveite a API e explore as funcionalidades disponíveis!!

## Endpoints
- POST /feedback
  - Corpo da requisição:
  ```json
   {
	"feedback": "Gostei muito da Alumind, só queria uma funcionalidade que permitisse eu alterar meu perfil."
   }
  ```
  - Retorno:
  ```json
  {
	"id": 1,
	"sentiment": "POSITIVO",
	"requestedFeatures": [
		{
			"code": "EDITAR_PERFIL",
			"reason": "O usuário gostaria de alterar seu perfil."
		}
	],
	"customResponse": "Obrigado pelo seu feedback positivo! Vamos considerar sua sugestão de implementar a opção de editar o perfil."
  }
  ```

## Requisitos de sistema

- Possuir a JDK 17 (o projeto foi desenvolvido com a versão 17 do java)
- IDE do IntelliJ
- SGBD do MySQL com um usuário e senha cadastrados

## Dúvidas ao desenvolver o projeto e explicação das decisões tomadas

<p align="justify">A principal dúvida que surgiu no projeto foi referente aos códigos únicos da funcionalidades sugeridas. Existem dois cenários para interpretação que impactam diretamente nos custos e performance do projeto. São eles:</p>

- Os códigos podem repetir se forem de feedbacks distintos

<p align="justify">Nesta abordagem, um código de funcionalidade sugerida pode aparecer em vários feedbacks distintos. Com isso, a modelagem do banco de dados é simplificada, seguindo uma estrutura 1:N , onde um feedback pode ter várias funcionalidades sugeridas, mas cada funcionalidade está relacionada diretamente a um feedback. Essa opção apresenta complexidade de desenvolvimento mais baixa, pois não é necessário garantir a exclusividade dos códigos globalmente. Sendo necessário um único prompt, o que reduz o consumo de tokens e latência da aplicação, resultando em menores custos operacionais. Posteriormente, quando for necessário criar a nova feature, pode ser feito uma listagem por código e assim criar um prompt que gere uma nova reason tendo como base todas as reasons com o mesmo código, gerando uma reason que englobe todos os casos, em seguida deixar no banco de dados apenas um registro com esse código e a nova reason associada a ele.</p> 

- O código não pode repetir, mesmo se for de feedbacks dintintos

<p align="justify">Neste caso a modelagem das tabelas ficaria N:N ou seja um feedback pode ter muitas funcionalidades sugeridas e uma funcionalidade sugerida pode ter muitos feedbacks. 
Seria preciso analisar cada nova funcionalidade sugerida verificando se já tem o código cadastrado no banco de dados, no caso de ocorrer seria preciso alinhar com o time o requisito de ‘o que fazer com o reason dessa nova funcionalidade’ (ignorar, gerar um novo prompt que mescla o que tem no banco de dados com o que está vindo, etc) e, por fim, vincular a funcionalidade sugerida que já está cadastrada com o novo feedback. Nesse caso o custo seria um pouco maior, bem como a latência do sistema por ter duas requisições à API da OpenAI por requisição à rota da AluMind, isso se o requisito exigir a criação do novo prompt.</p>

<p align="justify">Em um cenário real eu conversaria com a equipe e envolvidos para poder alinhar o que realmente seria preciso, com o requisito claro eu daria procedimento na implementação. No desafio, ao analisar os dois casos optei pelo caso 1, por ser mais barato e ter uma performance maior. Sendo assim um feedback pode ter várias funcionalidades sugeridas e cada funcionalidade sugerida tem relação com um único feedback. Abaixo tem-se uma imagem que demonstra como ficou modelado o relacionamento das tabelas: </p>

<p align="center">
  <img src="https://github.com/alissonjaques/imagens-aplicacoes/blob/main/alumind-api/esquema-banco-de-dados.png" alt="diagrama de entidade relacionamento do bando de dados da AluMind">
</p>

## Sobre a funcionalidade de identifação de spam e o promt principal gerado para a LLM
<p align="justify">A funcionalidade para verificar spam e a mensagem customizada de resposta foram adicionadas ao prompt, sendo feita uma única requisição à API da OpenAI. O prompt cobre todos os casos propostos no desafio. Para a criação do prompt foi levado em consideração várias técnicas como Few-Shot Prompting, Chain of Thought Prompting, Instruction Following, Prompt Engineering para Instruções Diretas, Use of Constraints, Output Formatting, Conditional Generation, Exemplos Negativos e Positivos, entre outros. Foi criado um único prompt por que manter o contexto em diferentes requisições aumenta consideravelmente a complexidade e o tempo para entregar a feature do projeto, pois seria preciso manter o contexto em diferentes prompts com a menor quantidade de tokens possível.</p>

## Sugestão de nova feature
<p align="justify">Desenvolver um sistema de gamificação com Inteligência Artificial que incentive o bem-estar mental dos usuários, criando uma abordagem personalizada que promova atividades como meditação regular e sessões terapêuticas. Esse sistema pode adaptar as ações e recompensas ao perfil de cada usuário, tornando a experiência mais significativa e motivadora.</p>

<p align="justify">A implementação requer a escolha de um modelo de linguagem grande (LLM) capaz de processar o volume e a complexidade dos dados da AluMind. Esse modelo deve ter a capacidade de entender o contexto e as necessidades individuais para sugerir atividades relevantes, além de fornecer feedback em tempo real. Para isso, é essencial empregar algoritmos de Aprendizado de Máquina em Grande Escala, como redes neurais profundas (Deep Learning), que possibilitam a análise de padrões complexos nos dados dos usuários. Esse processamento permitirá que o sistema evolua com as interações e aumente a eficácia das recomendações ao longo do tempo.</p>

## Sobre a funcionalidade de respostas customizadas
<p align="justify">A resposta customizada foi adicionada no json de resposta da rota de criar feedbacks, é retornado um campo customResponse que a representa. A escolha de manter na mesma rota foi devido à minha escolha de manter o mesmo contexto (para deixar a resposta mais coesa e rica), sendo gerada no mesmo prompt que analisa o feedback, assim a latência diminui e fica mais prático para quem está consumindo a API, pois não precisa  integrar e gerenciar com uma nova rota.</p>

## Modelo da LLM escolhido
<p align="justify">Foi escolhido para esse projeto o modelo 4o-mini da api da OpenAI, testei e analisei diferentes modelos e escolhi ele por ser o modelo mais barato que mantém a qualidade das respostas.</p>

<p align="center">
  <img src="https://github.com/alissonjaques/imagens-aplicacoes/blob/main/alumind-api/ai.jpg" alt="imagem de arte com inteligência artificial" style="width: 50%;">
</p>
