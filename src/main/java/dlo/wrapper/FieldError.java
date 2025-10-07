package dlo.wrapper;

public record FieldError(
        String rejectedValue,
        Object rejectValue,
        String message

){
}
