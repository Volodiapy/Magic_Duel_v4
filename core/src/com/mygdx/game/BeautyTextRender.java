package com.mygdx.game;

import java.util.ArrayList;

public class BeautyTextRender {
    String text1s = "Welcome little dark soul.\nHmm, it must be great to \nwake up from nothingness.";
    String text2s = "But you're here for a reason. \n I need to get you into the world of the living. \nIt doesn't matter why yet. But the monks \nfrom the cemetery in which you are buried \nprevent me from doing this";
    String text3s = "KILL THEM!";
    String text999s = "Are you back? Bad work, try again";
    int na = 0;
    public String[] text1 = new String[(text1s.split("")).length];
    public String[] text2 = new String[(text2s.split("")).length];
    public String[] text3 = new String[(text3s.split("")).length];
    public String[] text999 = new String[(text999s.split("")).length];
    public void init(){
        text1 = text1s.split("");
        text2 = text2s.split("");
        text3 = text3s.split("");
        text999 = text3s.split("");
    }

    public String retstring(int a, String b){

        if(a == 1 & na < text1.length){
            na += 1;
            return b + text1[na-1];
        }
        else if(a == 2 & na < text2.length){
            na += 1;
            return b + text2[na-1];
        }
        else if(a == 999 & na < text999.length){
            na += 1;
            return b + text999[na-1];
        }
        else if(a > 2){
            return "KILL THEM!";
        }
        else {
            return b;
        }
    }
    public void newtext(){
        na = 0;
    }
}
