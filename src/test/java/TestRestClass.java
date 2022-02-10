
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
import utils.BuildJsonUtil;
import utils.CommonUtils;

public class TestRestClass {

    static final String key =
        "Bearer t1.9euelZrOjpHKiY7JkZDHi5OOlYyKlO3rnpWakciXisjMlZWPzsvMmMySjZjl8_cWf2tv-e8LAjlp_N3z91YtaW_57wsCOWn8.6CZSfxmfnmgVu9VP5mO0OoPqNiZ_MM6exyG06OY1Vao4U8EgJL6H1ANXMsmYt1DKH-B3PojzGlCwFF4vpbvcAw";

    static final String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";

    static final OkHttpClient client = new OkHttpClient();

    @Test
    public void whenSendPostRequest_thenCorrectTest_id01()
        throws IOException {
        Call call = client.newCall(CommonUtils.createRequest((BuildJsonUtil.buildJson("b1g21kjlspgecjh6if8h", "Automated testing", "ru")), url, key));
        Response response = call.execute();
        Object obj = new JsonParser().parse(response.body().string());
        JsonObject jo = (JsonObject) obj;
        String text = jo.get("translations").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
        System.out.println(text);
    }

    @Test
    public void whenSendPostRequest_thenCorrectTest_id02()
        throws IOException {
        Call call = client.newCall(CommonUtils.createRequest((BuildJsonUtil.buildJson("b1g21kjlspgecjh6if8h", "Automated testing", "damn")), url, key));
        Response response = call.execute();
        assertEquals(400, response.code());
    }

    @Test
    public void whenSendPostRequest_thenCorrectTest_id03()
        throws IOException {
        Call call = client.newCall(CommonUtils.createRequest((BuildJsonUtil.buildJson("b1g21kjlspgecjh6if8h", "abracadabra", "ddd")), url, key));
        Response response = call.execute();
        assertEquals(400, response.code());
    }



}
