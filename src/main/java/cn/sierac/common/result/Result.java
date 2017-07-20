package cn.sierac.common.result;

import org.springframework.http.HttpStatus;

/**
 * author zhming
 */
public class Result {
    private int httpCode;
    private HttpStatus httpStatus;
    private Object body;

    public Result(int httpCode, Object body, HttpStatus httpStatus) {
        this.httpCode = httpCode;
        this.body = body;
        this.httpStatus = httpStatus;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
