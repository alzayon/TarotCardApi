package com.alexis.tarotapp.api.dto.pagination;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class PaginationDto {
    @DefaultValue("0")
    @QueryParam("start")
    public int start;

    @DefaultValue("10")
    @QueryParam("limit")
    public int limit;
}
