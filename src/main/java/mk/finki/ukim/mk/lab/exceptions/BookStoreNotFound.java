package mk.finki.ukim.mk.lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookStoreNotFound extends RuntimeException{
    public BookStoreNotFound(Long id){
        super(String.format("BookStore not found %s", id));
    }
}
