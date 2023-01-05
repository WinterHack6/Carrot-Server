package carrot.global.error;

import carrot.market.Item.exception.ItemNotFoundException;
import carrot.market.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response itemNotFoundException(ItemNotFoundException e) {
        return Response.failure(-3000, "해당 id의 아이템이 존재하지 않습니다.");
    }

}
