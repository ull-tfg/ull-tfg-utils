package es.ull.utils.rest.pagination;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.data.domain.Page;

public class UllPagination {

    public static String HEADER_PAGINATION_COUNT = "Pagination-Count";
    public static String HEADER_PAGINATION_SIZE = "Pagination-Size";
    public static String HEADER_PAGINATION_PAGE = "Pagination-Page";
    public static String HEADER_TOTAL_COUNT = "Total-Count";

    public static List<String> getExposedHeaders() {
        return Arrays.asList(new String[]{
            HEADER_PAGINATION_COUNT,
            HEADER_PAGINATION_SIZE,
            HEADER_PAGINATION_PAGE,
            HEADER_TOTAL_COUNT
        });
    }

    public static HttpHeaders toHeaders(Page page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_PAGINATION_COUNT, "" + page.getTotalPages());
        headers.add(HEADER_PAGINATION_SIZE, "" + page.getSize());
        headers.add(HEADER_PAGINATION_PAGE, "" + page.getNumber());
        headers.add(HEADER_TOTAL_COUNT, "" + page.getTotalElements());
        return headers;
    }
}
