//Solution below slightly adapted from https://github.com/Wincenttjoi/CS2103T-duke-chatbot/blob/master/src/main/java/duke/Duke.java

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private boolean exit;

    public Duke () {
        this.ui = new Ui();
        this.taskList = new TaskList();
        exit = false;
    }

    public enum InputCommands {
        bye, list, done, delete, todo, deadline, event
    }

    public void start () {
        System.out.println(ui.greet());
        while (!exit) {
            try {
                String userCommand = ui.echoCommand();

                if (userCommand.equals("bye")) {
                    System.out.println(ui.exit());
                    exit = true;
                } else if (userCommand.equals("list")) {
                    System.out.println(ui.retrieveList());
                } else if (userCommand.startsWith("done")) {
                    InputHandler doneInputHandler = new DoneInput(ui, taskList);
                    System.out.println(doneInputHandler.handle(userCommand));
                } else if (userCommand.startsWith("delete")) {
                    InputHandler deleteInputHandler = new DeleteInput(ui, taskList);
                    System.out.println(deleteInputHandler.handle(userCommand));
                } else if (userCommand.startsWith("todo")) {
                    InputHandler todoInputHandler = new TodoInput(ui, taskList);
                    System.out.println(todoInputHandler.handle(userCommand));
                } else if (userCommand.startsWith("deadline")) {
                    InputHandler deadlineInputHandler = new DeadlineInput(ui, taskList);
                    System.out.println(deadlineInputHandler.handle(userCommand));
                } else if (userCommand.startsWith("event")) {
                    InputHandler eventInputHandler = new EventInput(ui, taskList);
                    System.out.println(eventInputHandler.handle(userCommand));
                } else if (userCommand.equals("listInputs")) {
                    for (InputCommands inputs : InputCommands.values()) {
                        System.out.println(inputs);
                    }
                }
                else {
                    throw new UnknownInputException("error");
                }
            } catch (DukeException e) {
                ui.getError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
