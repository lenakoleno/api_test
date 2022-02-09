import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;

public class TestRestClass {

    final String key =
        "Bearer t1.9euelZqLyJbNz46Wl5WOj5qPy8uUlu3rnpWakciXisjMlZWPzsvMmMySjZjl9Pd_f29v-e9kI3Dp3fT3Py5tb_nvZCNw6Q.7DRsxhzMQdShWyCDngx1tG0TxXUWi3Zzu3hV9Sspp7kU2tmtxjBtVxP25Q5Bj8w-OxFyELMb4CQxJTv6wWI7CQ";

    final String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";

    @Test
    public void whenSendPostRequest_thenCorrect()
        throws IOException {
        String json = "{"
            + "    \"folderId\": \"b1g21kjlspgecjh6if8h\",\n"
            + "    \"texts\": [\"Automated testing\"],\n"
            + "    \"targetLanguageCode\": \"ru\"\n"
            + "}";

        RequestBody body = RequestBody.create(
            MediaType.parse("application/json"),
            json
        );

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(createRequest(body));
        Response response = call.execute();

        Object obj = new JsonParser().parse(response.body().string());
        JsonObject jo = (JsonObject) obj;
        String text = jo.get("translations").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
        System.out.println(text);
    }

    public Request createRequest(RequestBody body) {
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", key)
            .build();
        return request;
    }
}
