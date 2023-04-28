package es.ull.utils.rest.pagination;

import es.ull.utils.rest.error.ApiSubError;
import es.ull.utils.rest.error.ApiSubErrorMessage;

import es.ull.utils.random.UllRandom;

import java.util.List;

public class UllRequestParameterValidation {

    // page
    public static final int PAGE_DEFAULT_VALUE = 0;
    public static final int PAGE_MIN_VALUE = 0;
    public static final int PAGE_MAX_VALUE = Integer.MAX_VALUE - 1;
    public static final int MAXIMUM_NUMBER_OF_PAGES = PAGE_MAX_VALUE - PAGE_MIN_VALUE + 1;
    public static final String ERROR_PAGE_WRONG_FORMAT = "Value of request parameter '" + UllPagination.PAGE
            + "'' must be numeric";
    public static final String ERROR_PAGE_MIN_VALUE = "Value of request parameter '" + UllPagination.PAGE
            + "'' must be non-negative";
    public static final String ERROR_PAGE_MAX_VALUE = "Value of request parameter '" + UllPagination.PAGE
            + "'' must be at most " + PAGE_MAX_VALUE;
    // size
    public static final int SIZE_DEFAULT_VALUE = 20;
    public static final int SIZE_MIN_VALUE = 1;
    public static final int SIZE_MAX_VALUE = 100;
    public static final String ERROR_SIZE_WRONG_FORMAT = "Value of request parameter '" + UllPagination.SIZE
            + "'' must be numeric";
    public static final String ERROR_SIZE_MIN_VALUE = "Value of request parameter '" + UllPagination.SIZE
            + "'' must be positive";
    public static final String ERROR_SIZE_MAX_VALUE = "Value of request parameter '" + UllPagination.SIZE
            + "'' must be at most " + SIZE_MAX_VALUE;

    public static int validateRequestParameterPage(String page, List<ApiSubError> errors) {
        if (page == null) {
            return PAGE_DEFAULT_VALUE;
        }
        page = page.trim();
        if (page.isEmpty()) {
            return PAGE_DEFAULT_VALUE;
        }
        int value = PAGE_DEFAULT_VALUE;
        try {
            value = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            errors.add(new ApiSubErrorMessage(ERROR_PAGE_WRONG_FORMAT));
        }
        if (value < PAGE_MIN_VALUE) {
            value = PAGE_MIN_VALUE;
        }
        if (value > PAGE_MAX_VALUE) {
            value = PAGE_MAX_VALUE;
        }
        return value;
    }

    public static int validateRequestParameterSize(String size, List<ApiSubError> errors) {
        if (size == null) {
            return SIZE_DEFAULT_VALUE;
        }
        size = size.trim();
        if (size.isEmpty()) {
            return SIZE_DEFAULT_VALUE;
        }
        int value = SIZE_DEFAULT_VALUE;
        try {
            value = Integer.parseInt(size);
        } catch (NumberFormatException e) {
            errors.add(new ApiSubErrorMessage(ERROR_SIZE_WRONG_FORMAT));
        }
        if (value < SIZE_MIN_VALUE) {
            value = SIZE_MIN_VALUE;
        }
        if (value > SIZE_MAX_VALUE) {
            value = SIZE_MAX_VALUE;
        }
        return value;
    }

    public static int randomPage() {
        return UllRandom.random(
                UllRequestParameterValidation.PAGE_MIN_VALUE,
                UllRequestParameterValidation.PAGE_MAX_VALUE + 1);
    }

    public static int randomSize() {
        return UllRandom.random(
                UllRequestParameterValidation.SIZE_MIN_VALUE,
                UllRequestParameterValidation.SIZE_MAX_VALUE + 1);
    }
}