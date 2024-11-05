package io.github.unawarespecs.parser;

import lombok.Data;

/*
    Statement  -->  SetCmd | AddCmd
    SetCmd     -->  SET Property=Value
    Property   --> FILL | COLOR | THICKNESS | SHAPEMODE | DRAWMODE
    Value      --> NUM
    AddCmd     --> NUM, NUM - NUM, NUM
 */
@Data
public class CommandParser{

    String str;
    int index;
    int lookahead;
    int token;
    int tokenval;
    String strval;
    static int INVALID =65536;
    static int NUM = 65535;
    static int SET = 65534;
    static int FILL = 65533;
    static int THICKNESS = 65532;
    static int SHAPEMODE = 65531;
    static int DRAWMODE = 65530;
    static int COLOR = 65529;
    static int EOS=65528;

    int startX;
    int startY;
    int endX;
    int endY;
    int len=0;
    public CommandParser(){
        index = 0;
    }

    public void parse(String str){
        this.str = str;
        len = str.length();
        lookahead = lexan();
        if(lookahead==SET){
            match(SET);
            Property();
            match('=');
            match(NUM);
        } else if(lookahead==NUM){
            startX = tokenval;
            match(NUM);
            match(',');
            startY = tokenval;
            match(NUM);
            match('-');
            endX = tokenval;
            match(NUM);
            match(',');
            endY = tokenval;
            match(NUM);
        }
    }

     void error(String str){
        System.out.println(str);
     }

    void  match(int tok){
        if(lookahead == tok){
            lookahead = lexan();
        } else {
            error("Error occurred");
        }
        return ;
    }
    int lexan(){
        int start = 0;
        while(index < len){
            Character ch = str.charAt(index);
            if(Character.isSpaceChar(ch)){
                index++;
                continue;
            }
            if (Character.isDigit(ch)){
                start = index;
                index++;
                ch = str.charAt(index);
                while(index < len &&  Character.isDigit(ch)){
                    index++;
                    if(index==len) {
                        break;
                    }
                    ch = str.charAt(index);
                }
                String val = str.substring(start, index);
                tokenval = Integer.parseInt(val);
                return NUM;
            } else if(Character.isLetter(ch)){
                start = index;
                index++;
                while(Character.isLetter(ch)){
                    index++;
                    ch = str.charAt(index);
                }
                strval = str.substring(start, index);
                if(strval.equals("SET"))
                    return SET;
                if(strval.equals("FILL"))
                    return FILL;
                if(strval.equals("COLOR"))
                    return COLOR;
                if(strval.equals("THICKNESS"))
                    return THICKNESS;
                if(strval.equals("SHAPEMODE"))
                    return SHAPEMODE;
                if(strval.equals("DRAWMODE"))
                    return DRAWMODE;
            }
            else {
                index++;
                return ch;
            }
        }
        return EOS;
    }
    void Property(){
        if(lookahead == FILL){
            match(FILL);
        } else if(lookahead == COLOR){
            match(COLOR);
        }

    }
}
