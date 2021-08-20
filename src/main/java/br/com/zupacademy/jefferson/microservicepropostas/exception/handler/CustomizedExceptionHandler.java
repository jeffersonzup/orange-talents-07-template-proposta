package br.com.zupacademy.jefferson.microservicepropostas.exception.handler;

import br.com.zupacademy.jefferson.microservicepropostas.exception.DocumentDuplicationException;
import br.com.zupacademy.jefferson.microservicepropostas.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(DocumentDuplicationException.class)
    public final ResponseEntity<ExceptionResponse> handleDocumentDuplicationException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionResponse> handleAllBadRequestExceptions(
            MethodArgumentNotValidException methodArgumentNotValidException, WebRequest request){
        Collection<String> mensagens = new ArrayList<>();
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String message = String.format("Campo %s: %s ", fieldError.getField(), fieldError.getDefaultMessage());
            mensagens.add(message);
        });
        ExceptionResponse messageList = new ExceptionResponse(
                LocalDateTime.now(),
                request.getDescription(false),
                mensagens);
        return new ResponseEntity<>(messageList, HttpStatus.BAD_REQUEST);
    }
}
