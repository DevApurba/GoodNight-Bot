import net.dv8tion.jda.api.EmbedBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.util.Random;

public class QuoteApiRequest {
    final String URL = "https://api.quotable.io/random?";
    String data;
    Response response;
    EmbedBuilder builder;
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request ;
    JSONParser jsonParser = new JSONParser();

    public EmbedBuilder randomQuotes(String avatarUrl, String botName) {
        builder = new EmbedBuilder();

        try {
            request = new Request.Builder().url(URL).build();
            response = okHttpClient.newCall(request).execute();
            data = response.body().string();
            JSONObject randomObject = (JSONObject)jsonParser.parse(data);
            final String CONTENT = String.valueOf(randomObject.get("content"));

            builder.setAuthor(botName, "https://discord.com", avatarUrl).setThumbnail(avatarUrl).addField("Here is a quote before you go to sleep", CONTENT ,true).setFooter("With \uD83D\uDC99 from Bot").setColor(randomColor());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }
    public static Color randomColor() {
        Random random = new Random();

        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r, g, b);
    }
}