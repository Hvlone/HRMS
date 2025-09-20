package com.example.demo.vo;

public class ApiResponse {
    private int code;       // 响应码
    private String message; // 响应消息
    private Object data;    // 响应数据

    private ApiResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应（无数据）
    public static ApiResponse success() {
        return new ApiResponse(200, "success", null);
    }

    // 成功响应（带消息）
    public static ApiResponse success(String message) {
        return new ApiResponse(200, message, null);
    }

    // 成功响应（带数据）
    public static ApiResponse success(Object data) {
        return new ApiResponse(200, "success", data);
    }

    // 成功响应（带消息和数据）
    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(200, message, data);
    }

    // 错误响应
    public static ApiResponse error(String message) {
        return new ApiResponse(500, message, null);
    }

    // Getter
    public int getCode() { return code; }
    public String getMessage() { return message; }
    public Object getData() { return data; }
}
