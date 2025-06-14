package com.course.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.course.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	//Lỗi not found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
		ApiResponse<String> response = new ApiResponse<>(404, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	//Lỗi sai định dạng json
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidFormat(HttpMessageNotReadableException ex) {
    	ApiResponse<String> response = new ApiResponse<>(400, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    //Lỗi validate
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		// Duyệt qua tất cả các lỗi của từng trường
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			// Ghi tên trường và thông báo lỗi tương ứng
			errors.put(error.getField(), error.getDefaultMessage());
		});
		ApiResponse<Map<String, String>> response = new ApiResponse<>(400, errors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	//Lỗi tồn tại
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequest(BadRequestException ex) {
		ApiResponse<String> response = new ApiResponse<>(400, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	//Lỗi param
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
	    String message = String.format("Invalid value '%s' for parameter '%s'", ex.getValue(), ex.getName());
	    ApiResponse<String> response = new ApiResponse<>(400, message);
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	//Lỗi thiếu param bắt buộc
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?> handleMissingParam(MissingServletRequestParameterException ex) {
	    String message = String.format("Missing required parameter: %s", ex.getParameterName());
	    ApiResponse<String> response = new ApiResponse<>(400, message);
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	//Lỗi code
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception ex) {
		ex.printStackTrace();
		ApiResponse<String> response = new ApiResponse<>(500, "Internal Server Error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
