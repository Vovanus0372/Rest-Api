import org.apache.log4j.BasicConfigurator;

import java.util.logging.Logger;

public class NoAppenderExample {
    private final static Logger logger = Logger.getLogger(String.valueOf(NoAppenderExample.class));

    public static void main(String[] args) {
        BasicConfigurator.configure();
        logger.info("Info log message");
    }

}