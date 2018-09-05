package ch.ethz.geco.t4j.internal.auth;

import java.util.Set;

public class Token {
    private final int REFRESH_TOKEN_EXPIRY = 14 * 24 * 60 * 60;
    private final int DEFAULT_ACCESS_TOKEN_EXPIRY = 90000;

    private final String accessToken;
    private final Long accessTokenExpiry;

    private final String refreshToken;
    private final Long refreshTokenExpiry;

    private final String tokenType;
    private final Set<Scope> scope;

    public Token(String accessToken, String tokenType, Set<Scope> scope, Long accessTokenExpiry) {
        this.accessToken = accessToken;
        this.accessTokenExpiry = (System.currentTimeMillis() / 1000L) + accessTokenExpiry;

        this.refreshToken = null;
        this.refreshTokenExpiry = null;

        this.tokenType = tokenType;
        this.scope = scope;
    }

    public Token(String accessToken, String tokenType, Set<Scope> scope) {
        this.accessToken = accessToken;
        this.accessTokenExpiry = (System.currentTimeMillis() / 1000L) + DEFAULT_ACCESS_TOKEN_EXPIRY;

        this.refreshToken = null;
        this.refreshTokenExpiry = null;

        this.tokenType = tokenType;
        this.scope = scope;
    }

    public Token(String accessToken, String tokenType, Set<Scope> scope, Long accessTokenExpiry, String refreshToken) {
        this.accessToken = accessToken;
        this.accessTokenExpiry = accessTokenExpiry;

        this.refreshToken = refreshToken;
        this.refreshTokenExpiry = (System.currentTimeMillis() / 1000L) + REFRESH_TOKEN_EXPIRY;

        this.tokenType = tokenType;
        this.scope = scope;
    }

    public Token(String accessToken, String tokenType, Set<Scope> scope, String refreshToken) {
        this.accessToken = accessToken;
        this.accessTokenExpiry = (System.currentTimeMillis() / 1000L) + DEFAULT_ACCESS_TOKEN_EXPIRY;

        this.refreshToken = refreshToken;
        this.refreshTokenExpiry = (System.currentTimeMillis() / 1000L) + REFRESH_TOKEN_EXPIRY;

        this.tokenType = tokenType;
        this.scope = scope;
    }

    /**
     * Returns the access token.
     *
     * @return The access token.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Returns the refresh token.
     *
     * @return The refresh token.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Returns the token type.
     *
     * @return The token type.
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Returns the scope of the token.
     *
     * @return the token scope.
     */
    public Set<Scope> getScope() {
        return scope;
    }

    /**
     * Returns whether the access token has expired.
     *
     * @return True if the access token has expired, false otherwise.
     */
    public boolean hasAccessExpired() {
        return (System.currentTimeMillis() / 1000L) > accessTokenExpiry;
    }

    /**
     * Returns whether the refresh token has expired.
     *
     * @return True if the refresh token has expired, false otherwise.
     */
    public boolean hasRefreshExpired() {
        return (System.currentTimeMillis() / 1000L) > refreshTokenExpiry;
    }

    /**
     * Returns whether this token is refreshable respectively if it has a refresh token.
     * It will only check if the refresh token exists and not if the token has expired.
     *
     * @return True if the token is refreshable, false otherwise.
     */
    public boolean isRefreshable() {
        return refreshToken != null;
    }
}
