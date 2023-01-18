package Commands;

import types.ICommand;

import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class Bye implements ICommand {
    public static final Pattern p = Pattern.compile("bye");
    private static final String outputText = "Bye. Hope to see you again soon!";

    @Override
    public void take(String s) {
        System.out.println(outputText);
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}
