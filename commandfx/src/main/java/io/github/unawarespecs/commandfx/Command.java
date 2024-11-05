package io.github.unawarespecs.commandfx;

public interface Command {
    void execute();
    void undo();
    void redo();
}
