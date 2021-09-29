package exceptions;

public class ValidationException extends Throwable{

    private Integer status;

    public ValidationException(String message, Integer status) {
        super(message);
        this.status = status;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
