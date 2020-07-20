package navercorp.com.andy.naver;

import lombok.ToString;

@ToString
public class Token {
    final public String access_token;
    final public String refresh_token;
    final public String token_type;
    final public String expires_in;

    public Token(String access_token, String refresh_token, String token_type, String expires_in) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }
}