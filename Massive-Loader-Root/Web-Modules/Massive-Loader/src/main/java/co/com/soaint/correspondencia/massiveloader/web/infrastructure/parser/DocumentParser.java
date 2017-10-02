package co.com.soaint.correspondencia.massiveloader.web.infrastructure.parser;

import co.com.soaint.foundation.infrastructure.exceptions.BusinessException;
import co.com.soaint.foundation.infrastructure.transformer.Transformer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by g2o on 31-Jul-17.
 */
public abstract class DocumentParser<O, E> {
    //[core] ------------------------------
    public abstract List<O> parse(MultipartFile file, Transformer<E, O> transformer) throws IOException, BusinessException;

}
