package navercorp.com.andy.naver;

import org.json.simple.JSONObject;

public class ProfileAPI {
    final static String apiURL = "https://openapi.naver.com/v1/nid/me";

    public static Profile get(String access_token) {
        HTTPRequestManager request = new HTTPRequestManager(apiURL, HTTPRequestManager.METHOD.GET);
        request.setRequestHeader("Authorization", "Bearer " + access_token);

        JSONObject json = null;
        try {
            json = request.getJsonResponse();
        } catch (Exception e) {
            System.out.println("failed to get and parse naver profile response" + e.getMessage());
            e.printStackTrace();
            return null;
        }

        String id = (String) ((JSONObject)json.get("response")).get("id");
        String nickname = (String) ((JSONObject)json.get("response")).get("nickname");
        String profile_image = (String) ((JSONObject)json.get("response")).get("profile_image");
        String name = (String) ((JSONObject)json.get("response")).get("name");

        return new Profile(id, nickname, profile_image, name);
    }
}
