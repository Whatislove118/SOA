package exceptions;

import java.util.ArrayList;

public class ValidationArrayException extends Throwable {
    private Integer status;
    public ArrayList<ValidationException> list;

    public ValidationArrayException(ArrayList<ValidationException> list, Integer status) {
        super();
        this.list = list;
        this.status = status;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
