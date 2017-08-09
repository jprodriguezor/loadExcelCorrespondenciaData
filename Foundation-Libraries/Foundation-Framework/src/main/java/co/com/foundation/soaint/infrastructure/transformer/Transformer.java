package co.com.foundation.soaint.infrastructure.transformer;

public interface Transformer<I,O> {

    O transform(I input);

}
