package co.com.soaint.foundation.infrastructure.transformer;

public interface Transformer<I,O> {

    O transform(I input);

}
