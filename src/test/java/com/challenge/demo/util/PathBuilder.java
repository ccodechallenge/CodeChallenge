package com.challenge.demo.util;

import org.springframework.data.util.Pair;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by nurisezgin on 12.01.2020.
 */
public final class PathBuilder {

    private static final String PATH_FORMAT = "http://localhost:%d/";
    private static final String QUERY_PARAM_FORMAT = "%s=%s";
    public static final String STR_QUESTION_MARK = "?";
    public static final String STR_AMPERSAND = "&";
    private final StringBuilder pathBuilder;

    private PathBuilder(int port) {
        final String baseURI = String.format(PATH_FORMAT, port);

        pathBuilder = new StringBuilder();
        pathBuilder.append(baseURI);
    }

    public static PathBuilder newBuilder(int port) {
        return new PathBuilder(port);
    }

    public PathBuilder addApiExtension(String extension) {
        pathBuilder.append(extension);
        return this;
    }

    public PathBuilder addQueryParam(Pair<String, String> keyValue) throws URISyntaxException {
        String separator = shouldHasQueryParam() ? STR_AMPERSAND : STR_QUESTION_MARK;
        pathBuilder.append(separator);

        final String queryParam = String.format(QUERY_PARAM_FORMAT,
                keyValue.getFirst(), keyValue.getSecond());

        pathBuilder.append(queryParam);

        return this;
    }

    public String build() {
        return pathBuilder.toString();
    }

    private boolean shouldHasQueryParam() throws URISyntaxException {
        String path = pathBuilder.toString();
        URI uri = new URI(path);
        String rawQuery = uri.getRawQuery();

        return rawQuery != null && !rawQuery.isEmpty();
    }

}
