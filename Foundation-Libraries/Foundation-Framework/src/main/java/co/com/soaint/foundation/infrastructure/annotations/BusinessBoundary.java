package co.com.soaint.foundation.infrastructure.annotations;

import co.com.soaint.foundation.infrastructure.exceptions.BusinessException;
import co.com.soaint.foundation.infrastructure.exceptions.SystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW,
        rollbackFor = {BusinessException.class, SystemException.class})
public @interface BusinessBoundary {
}
