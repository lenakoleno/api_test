package utils;

public final class BuildJsonUtil {
    private BuildJsonUtil() {
    }
    public static String buildJson(String folderId, String texts, String targetLanguageCode) {
        String json = "{"
            + "    \"folderId\": \"" + folderId + "\",\n"
            + "    \"texts\": [\"" + texts + "\"],\n"
            + "    \"targetLanguageCode\": \"" + targetLanguageCode + "\"\n"
            + "}";
        return json;
    }
}
