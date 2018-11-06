package controller;

/**
 * Created by hhx on 2017/4/7.
 */
public class UnauthorizedException extends Exception {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
        super();
    }
}
