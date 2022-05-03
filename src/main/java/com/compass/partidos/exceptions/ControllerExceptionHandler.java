package com.compass.partidos.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDto> erroHandler(MethodArgumentNotValidException exception) {

        List<ErroDto> erroDto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ErroDto errors = new ErroDto(fieldError.getField(), mensagem);
            erroDto.add(errors);
        });
        return erroDto;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AssociadoNotFoundException.class)
    public ErrorMessage asssociadoNotFound(AssociadoNotFoundException exception) {

        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getLocalizedMessage(),
                HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AssociadoNaoVinculadoException.class)
    public ErrorMessage asssociadoNaoVinculado(AssociadoNaoVinculadoException exception) {

        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PartidoNotFoundException.class)
    public ErrorMessage partidoNotFound(PartidoNotFoundException exception) {

        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getLocalizedMessage(),
                HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorMessage httpNotReadable(HttpMessageNotReadableException exception) {

        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase());
    }



}
