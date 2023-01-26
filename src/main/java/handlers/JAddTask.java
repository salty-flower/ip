package handlers;

import services.TaskList;
import types.IHandler;
import types.data.Deadline;
import types.data.Event;
import types.data.Task;
import types.data.Todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class JAddTask implements IHandler {
    private static final Pattern todo_p = Pattern.compile("(todo) (.*)");
    private static final Pattern event_p = Pattern.compile("(event) (.*) /from (.*) /to (.*)");
    private static final Pattern deadline_p = Pattern.compile("(deadline) (.*) /by (.*)");
    private static final Pattern p = Pattern
            .compile("(todo) (.*)|(deadline) (.*) /by (.*)|(event) (.*) /from (.*) /to (.*)");
    private final TaskList ts;

    public JAddTask(TaskList ts) {
        this.ts = ts;
    }

    @Override
    public String take(String s) {
        if (!p.matcher(s).matches()) {
            return "";
        }

        Matcher m;
        Task t = null;

        m = todo_p.matcher(s);
        if (m.matches()) {
            ts.addTask(t = Todo.create(m.group(2)));
        }

        m = event_p.matcher(s);
        if (m.matches()) {
            ts.addTask(t = Event.create(m.group(2), m.group(3), m.group(4)));
        }

        m = deadline_p.matcher(s);
        if (m.matches()) {
            ts.addTask(t = Deadline.create(m.group(2), m.group(3)));
        }

        return String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.\n", t,
                ts.getTaskCount());
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}