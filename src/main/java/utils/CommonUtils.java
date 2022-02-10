package utils;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public final class CommonUtils {
    private CommonUtils() {
    }
    public static Request createRequest(String json, String url, String key) {
        RequestBody body = RequestBody.create(
            MediaType.parse("application/json"),
            json
        );
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", key)
            .build();
        return request;
    }
}
