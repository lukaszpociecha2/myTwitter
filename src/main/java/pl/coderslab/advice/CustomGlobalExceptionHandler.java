package pl.coderslab.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

   /* @ExceptionHandler({ConstraintViolationException.class})
    public void constraintViolationException(HttpServletResponse response, ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        if(!violations.isEmpty()){
            violations.stream().forEach((k)->{
                System.out.println(k.getPropertyPath() + " : " + k.getMessage());
            });
        }

    }
*/
}
