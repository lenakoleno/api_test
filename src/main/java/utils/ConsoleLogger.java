package utils;

import okhttp3.logging.HttpLoggingInterceptor.Logger;

public class ConsoleLogger implements Logger {

    @Override
    public void log(String s) {
        System.out.println(s);
    }
}
