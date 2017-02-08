package io.mindjet.jetutil.log;

import com.orhanobut.logger.Logger;

/**
 * Created by Jet on 2/8/17.
 */

public class LoggerFactory {

    public static void init() {
        Logger.init().hideThreadInfo();
    }



}
