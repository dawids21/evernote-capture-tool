package evernote_capture_tool;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.clients.UserStoreClient;
import com.evernote.edam.type.Note;

/**
 * EvernoteAccount
 */
class EvernoteAccount {
    private UserStoreClient userStore;
    private NoteStoreClient noteStore;
    private String newNoteGuid;
    
    EvernoteAccount(String token) throws Exception {
        // Set up the UserStore client and check that we can speak to the server
        EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.SANDBOX, token);
        ClientFactory factory = new ClientFactory(evernoteAuth);
        userStore = factory.createUserStoreClient();
    
        boolean versionOk = userStore.checkVersion("Evernote EDAMDemo (Java)",
            com.evernote.edam.userstore.Constants.EDAM_VERSION_MAJOR,
            com.evernote.edam.userstore.Constants.EDAM_VERSION_MINOR);
        if (!versionOk) {
          System.err.println("Incompatible Evernote client protocol version");
          System.exit(1);
        }
    
        // Set up the NoteStore client
        noteStore = factory.createNoteStoreClient();
    }

    void createNote(String captureData) throws Exception {
        // To create a new note, simply create a new Note object and fill in
        // attributes such as the note's title.
        Note note = new Note();
        note.setTitle("Capture");
    
        // The content of an Evernote note is represented using Evernote Markup
        // Language
        // (ENML). The full ENML specification can be found in the Evernote API
        // Overview
        // at http://dev.evernote.com/documentation/cloud/chapters/ENML.php
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">"
            + "<en-note>"
            + "<span style=\"color:black;\">"
            + captureData
            + "</span><br/>"
            + "</en-note>";
        note.setContent(content);
    
        // Finally, send the new note to Evernote using the createNote method
        // The new Note object that is returned will contain server-generated
        // attributes such as the new note's unique GUID.
        Note createdNote = noteStore.createNote(note);
        newNoteGuid = createdNote.getGuid();
    
        System.out.println("Successfully created a new note with GUID: "
            + newNoteGuid);
        System.out.println();
    }
}