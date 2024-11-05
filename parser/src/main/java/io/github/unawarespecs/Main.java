package io.github.unawarespecs;

import io.github.unawarespecs.parser.CommandParser;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       CommandParser cmdParser = new CommandParser();
   /*    cmdParser.parse("64,24-12,34");
       Point p = new Point();
       p.x = cmdParser.getStartX();
       p.y = cmdParser.getStartY();
       Point end =new Point();
       end.x = cmdParser.getEndX();
       end.y = cmdParser.getEndY();
*/
       cmdParser.parse("SET COLOR=123");

    }
}