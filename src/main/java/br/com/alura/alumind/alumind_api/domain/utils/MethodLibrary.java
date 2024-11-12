package br.com.alura.alumind.alumind_api.domain.utils;

public class MethodLibrary {
    public static String formatJsonOpenAI(String json) throws Exception {
        String regex = "\\{.*\\}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex, java.util.regex.Pattern.DOTALL);
        java.util.regex.Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            String jsonObject = matcher.group();
            return jsonObject;
        }
        throw new Exception("Não foi possível converter o json da api da openai.");
    }
}
