package SEproject.hello.common.exception;

import SEproject.hello.common.model.BaseResponse;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	protected ResponseEntity<BaseResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		String defaultMessage = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		log.error("handle Method Argument Not Valid Exception : {}", defaultMessage);
		return ResponseEntity.status(400).body(new BaseResponse(defaultMessage, 400));
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<BaseResponse> handleException(Exception e) {
		log.error("Internal Error Trace : {} ", Arrays.toString(e.getStackTrace()));
		log.error("Error Message : {}", e.getMessage());
		return ResponseEntity.status(500).body(new BaseResponse(e.getMessage(), 500));
	}

	@ExceptionHandler(value = JwtException.class)
	public ResponseEntity<BaseResponse> handleJwtException(Exception e) {
		return ResponseEntity.status(401).body(new BaseResponse(e.getMessage(), 401));
	}

	@ExceptionHandler(value = MaxUploadSizeExceededException.class)
	public ResponseEntity<BaseResponse> handleSizeExceededException(Exception e) {
		return ResponseEntity.status(400).body(new BaseResponse("용량은 10MB 까지 가능합니다!", 400));
	}
}

