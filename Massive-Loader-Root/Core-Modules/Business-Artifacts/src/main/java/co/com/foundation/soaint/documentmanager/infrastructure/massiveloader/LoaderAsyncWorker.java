package co.com.foundation.soaint.documentmanager.infrastructure.massiveloader;

import co.com.foundation.soaint.infrastructure.annotations.InfrastructureService;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

@InfrastructureService
public class LoaderAsyncWorker<E> {

    private static Logger LOGGER = LogManager.getLogger(LoaderAsyncWorker.class.getName());

    public LoaderAsyncWorker() {
    }


    //[async service] -------------------------

    @Async
    public void process(final LoaderExecutor<E> executor, final List<E> domainList,
                        final MassiveLoaderType type, CallerContext callerContext) {
        LOGGER.info("starting async processing");
        executor.execute(domainList, type, callerContext);
    }

}
