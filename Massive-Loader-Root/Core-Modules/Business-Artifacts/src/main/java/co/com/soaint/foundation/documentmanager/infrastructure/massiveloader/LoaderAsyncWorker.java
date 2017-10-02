package co.com.soaint.foundation.documentmanager.infrastructure.massiveloader;

import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.soaint.foundation.infrastructure.annotations.InfrastructureService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

@InfrastructureService
@Log4j2
public class LoaderAsyncWorker<E> {

    //private static Logger LOGGER = LogManager.getLogger(LoaderAsyncWorker.class.getName());

    public LoaderAsyncWorker() {
    }


    //[async service] -------------------------

    @Async
    public void process(final LoaderExecutor<E> executor, final List<E> domainList,
                        final MassiveLoaderType type, CallerContext callerContext) {
        log.info("starting async processing");
        executor.execute(domainList, type, callerContext);
    }

}
