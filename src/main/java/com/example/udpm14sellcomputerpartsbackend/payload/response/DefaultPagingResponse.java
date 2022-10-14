package com.example.udpm14sellcomputerpartsbackend.payload.response;

import com.example.udpm14sellcomputerpartsbackend.contants.ResponseStatusContants;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class DefaultPagingResponse<T> {

    private Integer success;
    private String message;
    private List<T> data;
    private Pagination pagination;

    public static <T> DefaultPagingResponse<T> success(Page<T> page) {
        DefaultPagingResponse<T> response = new DefaultPagingResponse<>();
        response.setSuccess(ResponseStatusContants.SUCCESS.getCode());
        response.setMessage(ResponseStatusContants.SUCCESS.getMessage());
        response.setData(page.getContent());
        response.setPagination(Pagination.builder()
                .page(page.getPageable().getPageNumber())
                .pageSize(page.getPageable().getPageSize())
                .totalPage(page.getTotalPages())
                .totalItem(page.getTotalElements())
                .build());
        return response;
    }


}
