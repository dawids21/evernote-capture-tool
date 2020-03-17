package evernote_capture_tool;

public class App {
    private static final String AUTH_TOKEN = "S=s1:U=95c04:E=1783efc2d38:C=170e74affe8:P=1cd:A=en-devtoken:V=2:H=ba83a3eecd53f8d98de6b9b5653ce614";
    
    public static void main(String[] args) throws Exception {
        String capture = args[0];
        String token = AUTH_TOKEN;
        EvernoteAccount evernote = new EvernoteAccount(token);
        evernote.createNote(capture);
    }
}
