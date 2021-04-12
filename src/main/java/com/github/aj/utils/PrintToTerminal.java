package com.github.aj.utils;

import java.io.PrintStream;
import java.text.MessageFormat;

import org.springframework.stereotype.Service;

@Service
public class PrintToTerminal {

	private static final String CONSTANT_FORMAT = "{0} {1}";

	private PrintStream printer = System.out;

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String GREY = "\u001b[30;1m" ;
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE
	public static final String WARN = "[!]";
	public static final String ERROR = "[X]";
	public static final String INFO = "[>]";
	public static final String SUCC = "[S]";
	public static final String PROMPT = "[?]";

	public void success(String text) {
		printColored(MessageFormat.format(CONSTANT_FORMAT, SUCC, text), GREEN);
	}
	public void pompts(String text) {
		printColored(MessageFormat.format(CONSTANT_FORMAT, PROMPT, text), BLUE);
	}
	public void infos(String text) {
		printColored(MessageFormat.format(CONSTANT_FORMAT, INFO, text), GREY);
	}

	public void warns(String text) {
		printColored(MessageFormat.format(CONSTANT_FORMAT, WARN, text), YELLOW);
	}

	public void errors(String text) {
		printColored(MessageFormat.format(CONSTANT_FORMAT, ERROR, text), RED);
	}

	private void printColored(String text, String color) {
		printer.println(MessageFormat.format("{0} {1} {2}", color, text, ANSI_RESET));
	}

	public void demo() {
		infos("information");
		warns("warning");
		errors("error");
		success("success");
		pompts("pompt");
	}

}
