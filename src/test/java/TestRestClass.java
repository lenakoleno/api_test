import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Description;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.BuildJsonUtil;
import utils.CommonUtils;

import java.io.IOException;
import utils.ConsoleLogger;

import static org.testng.Assert.assertEquals;

public class TestRestClass {

    static final String key =
        "Bearer t1.9euelZqTlJrPl5qPmomUjJucmcyej-3rnpWakciXisjMlZWPzsvMmMySjZjl9PdyeGZv-e8LTxL63fT3Midkb_nvC08S-g.SFQCM4VIu8kOtAexS5sEWYrWhTOwJGOCYJAUfUahoFZ0nos4wTwm7Dd185p9Q6IydjdIDKlQUdyv4cH1a2cZDQ";

    static final String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";

    static final String automTestRu = "Автоматизированное тестирование";

    static final String automTestEn = "Automated testing";

    static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new ConsoleLogger());

    static final OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(logging)
        .build();

    @BeforeSuite
    public void setUp() {
        logging.setLevel(Level.BODY);
    }

    @Description(value = "positive test checking translation of russian word")
    @Test
    public void positiveTestCheckingTranslationRussianWord_ID01() throws IOException {
        Call call = client.newCall(
            CommonUtils.createRequest((BuildJsonUtil.buildJson("b1g21kjlspgecjh6if8h", "Automated testing", "ru")), url,
                key));
        Response response = call.execute();
        Object obj = new JsonParser().parse(response.body().string());
        JsonObject jo = (JsonObject) obj;
        String text = jo.get("translations").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
        assertEquals(response.code(), 200);
        assertEquals(text, automTestRu);
    }

    @Description(value = "positive test checking translation of english word")
    @Test
    public void positiveTestCheckingTranslationEnglishWord_ID02()
        throws IOException {
        Call call = client.newCall(CommonUtils.createRequest(
            (BuildJsonUtil.buildJson("b1g21kjlspgecjh6if8h", "Автоматизированное тестирование", "en")), url, key));
        Response response = call.execute();
        Object obj = new JsonParser().parse(response.body().string());
        JsonObject jo = (JsonObject) obj;
        String text = jo.get("translations").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
        assertEquals(response.code(), 200);
        assertEquals(text, automTestEn);
    }

    @Description(value = "negative test checking incorrect form of language setting(more than 3 symbols)")
    @Test
    public void negativeTestCheckingTranslationIfLanguageCodeIsIncorrect_ID03()
        throws IOException {
        Call call = client.newCall(
            CommonUtils.createRequest((BuildJsonUtil.buildJson("b1g21kjlspgecjh6if8h", "Automated testing", "damn")),
                url, key));
        Response response = call.execute();
        assertEquals(response.code(), 400);
    }

    @Description(value = "negative test checking incorrect form of language setting(unknown language)")
    @Test
    public void negativeTestCheckingTranslationIfLanguageCodeIsIncorrect_ID04()
        throws IOException {
        Call call = client.newCall(
            CommonUtils.createRequest((BuildJsonUtil.buildJson("b1g21kjlspgecjh6if8h", "abracadabra", "ddd")), url,
                key));
        Response response = call.execute();
        assertEquals(response.code(), 400);
    }

    @Description(value = "negative test checking incorrect form of language setting(numbers)")
    @Test
    public void negativeTestCheckingTranslationIfLanguageCodeIsIncorrect_ID05()
        throws IOException {
        Call call = client.newCall(
            CommonUtils.createRequest((BuildJsonUtil.buildJson("b1g21kjlspgecjh6if8h", "123", "123")), url, key));
        Response response = call.execute();
        assertEquals(response.code(), 400);
    }

    @Description(value = "negative test checking incorrect form of language setting(empty field) and text of word(empty field)")
    @Test
    public void negativeTestCheckingTranslationIfLanguageCodeIsIncorrect_ID06()
        throws IOException {
        Call call = client.newCall(
            CommonUtils.createRequest((BuildJsonUtil.buildJson("b1g21kjlspgecjh6if8h", " ", " ")), url, key));
        Response response = call.execute();
        assertEquals(response.code(), 400);
    }
}
