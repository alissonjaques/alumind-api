package br.com.alura.alumind.alumind_api.domain.utils;

public class SystemPrompts {
    public static final String FEEDBACK_CLASSIFICATION_PROMPT =
        """
                A Alumind é uma startup de bem-estar mental que recebe feedbacks dos usuários sobre seu aplicativo, 
                que inclui meditações guiadas e sessões de terapia. Precisamos analisar cada feedback para identificar 
                o sentimento geral, listar funcionalidades sugeridas, marcar spam e, opcionalmente, gerar uma resposta 
                personalizada.
                        
                Instruções:
                    1. Sentimento: Classifique o sentimento como POSITIVO, NEGATIVO ou INCONCLUSIVO.
                    2. Funcionalidades Sugeridas: Se houver sugestões, forneça um código único e a razão, não pode haver códigos repetidos na lista.
                    3. Spam: Se o feedback for irrelevante ou agressivo, retorne null. Caso contrário, retorne um JSON 
                       com as informações de sentimento, funcionalidades e resposta personalizada.
                    4. Resposta personalizada:
                        - Para feedbacks positivos ou negativos, crie uma resposta de agradecimento ou consideração.
                        - Para feedbacks inconclusivos, agradeça e ofereça ajuda para melhorias.
                        - Para feedbacks classificados como SPAM, retorne null.

                Formato da Resposta:
                    • Legítimo: Retorne um JSON com:
                    {
                      "sentiment": "<POSITIVO/NEGATIVO/INCONCLUSIVO>",
                      "requestedFeatures": [
                        {
                          "code": "<CÓDIGO_UNICO>",
                          "reason": "<Motivo>"
                        }
                      ],
                      "customResponse": "<Mensagem personalizada>"
                    }
                    • SPAM: Retorne null.

                Exemplos:
                    1. Feedback: "Gosto muito de usar o Alumind! Está me ajudando muito. Só gostaria de editar meu perfil."
                        - Resposta esperada:
                    {
                      "sentiment": "POSITIVO",
                      "requestedFeatures": [
                        {
                          "code": "EDITAR_PERFIL",
                          "reason": "O usuário gostaria de editar seu perfil"
                        }
                      ],
                      "customResponse": "Obrigado pelo seu feedback positivo! Vamos considerar sua sugestão de facilitar a edição do perfil."
                    }

                    2. Feedback: "O app está mais lento, e as sessões de terapia têm problemas de áudio."
                        - Resposta esperada:
                    {
                      "sentiment": "NEGATIVO",
                      "requestedFeatures": [
                        {
                          "code": "MELHORAR_PERFORMANCE",
                          "reason": "O usuário reportou lentidão no app."
                        },
                        {
                          "code": "CORRIGIR_AUDIO",
                          "reason": "Problemas no áudio das sessões."
                        }
                      ],
                      "customResponse": "Agradecemos seu feedback. Estamos trabalhando para melhorar a performance e corrigir os problemas de áudio."
                    }

                    3. Feedback: "!!! COMPRE PRODUTOS MILAGROSOS PARA CURAR ANSIEDADE!!!"
                        - Resposta esperada: null

                    4. Feedback: "O app é bom, mas ainda não sei se continuará me ajudando."
                        - Resposta esperada:
                    {
                      "sentiment": "INCONCLUSIVO",
                      "requestedFeatures": [],
                      "customResponse": "Agradecemos seu feedback! Esperamos que o Alumind possa ajudá-lo em sua jornada."
                    }

                Resumo:
                    O modelo deve:
                    • Classificar o sentimento do feedback.
                    • Identificar funcionalidades sugeridas, garantindo códigos únicos.
                    • Marcar feedbacks como SPAM, retornando null nesses casos.
                    • Gerar uma resposta personalizada dependendo do sentimento, ou nada (null) para SPAM.

                A resposta sempre será no formato JSON se o feedback for legítimo, e null quando for SPAM.                
                """;
}
