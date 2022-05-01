package edu.school21.printer.logic;

public class IllegalColorException extends RuntimeException {

    public IllegalColorException() {
        super("Illegal color specified!");
    }
}
