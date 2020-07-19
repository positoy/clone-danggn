package navercorp.com.andy.naver;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccessTokenAPI {

    final static String apiURL = "https://nid.naver.com/oauth2.0/token";

    private static Map<String,String> getCommonQueryParams() {
        Map<String,String> params = new HashMap<>();
        params.put(NaverConfiguration.KEY.client_id, NaverConfiguration.VAL.client_id);
        params.put(NaverConfiguration.KEY.client_secret, NaverConfiguration.VAL.client_secret);
        return params;
    }

    public static Token get(String authcode) {
        HTTPRequestManager request = new HTTPRequestManager(apiURL, HTTPRequestManager.METHOD.GET);

        Map<String,String> queryParams = getCommonQueryParams();
        queryParams.put(NaverConfiguration.KEY.grant_type, NaverConfiguration.VAL.grant_type_authorization_code);
        queryParams.put(NaverConfiguration.KEY.code, authcode);
        queryParams.put(NaverConfiguration.KEY.state, NaverConfiguration.VAL.state);

        JSONObject json = null;
        try {
            json = request.getJsonResponse();
        } catch (Exception e) {
            System.out.println("failed to get and parse accesstoken retrieve response" + e.getMessage());
            e.printStackTrace();
            return null;
        }

        String access_token = (String)json.get("access_token");
        String refresh_token = (String)json.get("refresh_token");
        String token_type = (String)json.get("token_type");
        String expires_in = (String)json.get("expires_in");

        return new Token(access_token, refresh_token, token_type, expires_in);
    }

    public static Token refresh(String refreshtoken) {
        HTTPRequestManager request = new HTTPRequestManager(apiURL, HTTPRequestManager.METHOD.GET);

        Map<String,String> queryParams = getCommonQueryParams();
        queryParams.put(NaverConfiguration.KEY.grant_type, NaverConfiguration.VAL.grant_type_refresh_token);
        queryParams.put(NaverConfiguration.KEY.refresh_token, refreshtoken);

        JSONObject json = null;
        try {
            json = request.getJsonResponse();
        } catch (Exception e) {
            System.out.println("failed to get and parse refreshtoken response" + e.getMessage());
            e.printStackTrace();
            return null;
        }

        String access_token = (String)json.get("access_token");
        String refresh_token = (String)json.get("refresh_token");
        String token_type = (String)json.get("token_type");
        String expires_in = (String)json.get("expires_in");

        return new Token(access_token, refresh_token, token_type, expires_in);
    }

    public static boolean delete(String access_token) {
        HTTPRequestManager request = new HTTPRequestManager(apiURL, HTTPRequestManager.METHOD.GET);

        Map<String,String> queryParams = getCommonQueryParams();
        queryParams.put(NaverConfiguration.KEY.grant_type, NaverConfiguration.VAL.grant_type_delete);
        queryParams.put(NaverConfiguration.KEY.access_token, access_token);
        queryParams.put(NaverConfiguration.KEY.service_provider, NaverConfiguration.VAL.service_provider);

        try {
            String response = request.getResponse();
        } catch (Exception e) {
            System.out.println("failed to get delete token response" + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
