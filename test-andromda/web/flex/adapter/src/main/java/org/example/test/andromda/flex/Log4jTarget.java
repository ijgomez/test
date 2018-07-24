package org.example.test.andromda.flex;

import org.apache.log4j.Logger;

import flex.messaging.log.AbstractTarget;
import flex.messaging.log.LogEvent;

public class Log4jTarget extends AbstractTarget {
	// log4j levels:   OFF - FATAL - ERROR - WARN - INFO - DEBUG - TRACE - ALL
    // blazeds levels:  NONE - FATAL - ERROR - WARN - INFO - DEBUG - ALL

    @Override
    public void logEvent(LogEvent event) {
        Logger log = Logger.getLogger(event.logger.getCategory());

        if (event.level >= LogEvent.ERROR)
            log.error(event.message, event.throwable);
        else if (event.level >= LogEvent.WARN)
            log.warn(event.message, event.throwable);
        else if (event.level >= LogEvent.INFO)
             log.info(event.message, event.throwable);
        else if (event.level >= LogEvent.DEBUG)
             log.debug(event.message, event.throwable);
        else
             log.trace(event.message, event.throwable);
    }
}
