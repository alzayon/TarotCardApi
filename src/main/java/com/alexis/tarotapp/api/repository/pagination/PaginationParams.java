package com.alexis.tarotapp.api.repository.pagination;

public class PaginationParams {
    private final int start;
    private final int limit;

    public PaginationParams(int start, int limit) {
        this.start = start;
        this.limit = limit;
    }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }
}
