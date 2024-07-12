package idblab.todaysmenu.common;

import idblab.todaysmenu.common.enums.ResponseType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static idblab.todaysmenu.common.enums.ResponseType.FAILURE;
import static idblab.todaysmenu.common.enums.ResponseType.SUCCESS;

@Getter
@NoArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    @Builder
    public Response(ResponseType responseType, T data) {
        this.code = responseType.getCode();
        this.message = responseType.getMessage();
        this.data = data;
    }

    public static Response success() {
        return Response.builder()
                .responseType(SUCCESS)
                .build();
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(SUCCESS, data);
    }

    public static <T> Response<String> failure(String message) {
        return new Response<>(FAILURE, message);
    }

    public static Response failure() {
        return Response.builder()
                .responseType(FAILURE)
                .build();
    }

}
