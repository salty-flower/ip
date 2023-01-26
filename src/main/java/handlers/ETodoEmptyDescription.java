package handlers;

import types.IHandler;

import java.util.regex.Pattern;

public class ETodoEmptyDescription implements IHandler {
    private static final Pattern p = Pattern.compile("todo");

    @Override
    public String take(String s) {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}
