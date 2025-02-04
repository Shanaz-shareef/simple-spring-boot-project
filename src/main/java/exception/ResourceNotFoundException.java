package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
     private String ResourceName;
     private String fieldName;
     private String fieldValue;

    public ResourceNotFoundException(String  resourceName, String fieldName, String fieldValue){
        super(String.format("%s not found with %s :%s"+resourceName,fieldName,fieldValue));
        this.ResourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;

    }


    public ResourceNotFoundException(String resourceName) {
    }
}
