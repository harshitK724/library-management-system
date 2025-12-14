package com.lms.service.search;

public class SearchStrategyFactory {
    public enum SearchType { TITLE, AUTHOR, ISBN }

    public static BookSearchStrategy get(SearchType type) {
        return switch (type) {
            case TITLE -> new TitleSearchStrategy();
            case AUTHOR -> new AuthorSearchStrategy();
            case ISBN -> new IsbnSearchStrategy();
        };
    }
}
