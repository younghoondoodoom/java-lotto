package lotto.validator;

public interface InputValidator<T> {

    boolean validate(T target);
}
