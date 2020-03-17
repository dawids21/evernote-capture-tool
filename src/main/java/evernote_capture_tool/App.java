package evernote_capture_tool;

import java.io.File;
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) throws Exception {
        File jarFile = new File(App.class.getProtectionDomain().getCodeSource().getLocation()
        .getFile());        
        String path = jarFile.getParent() + File.separator + "token.txt";
        File file = new File(path);
        Scanner input = new Scanner(file);
        String token = input.nextLine();
        input.close();
        String capture = args[0];
        EvernoteAccount evernote = new EvernoteAccount(token);
        evernote.createNote(capture);
    }
}
