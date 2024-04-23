import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HTTPRequestTest {
    public static void main(String[] args) throws IOException {
        OkHttpClient client =  new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://66266045052332d55322accb.mockapi.io/test/data/users")
                .build();

        try(Response response = client.newCall(request).execute()){
            if(response.isSuccessful()){
                String responseBody = response.body().string();
                System.out.println(responseBody);
            }else {
                System.out.println("error");
            }
        }
    }
}
