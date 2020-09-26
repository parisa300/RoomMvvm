package com.example.roommvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>>allNotes;
    public NoteRepository(Application application){
        NoteDatabase database=NoteDatabase.getInstance(application);
        noteDao=database.noteDao();
        allNotes=noteDao.getAllNotes();
    }
    public void insert(Note note){
    new InsertNoteAsynctask(noteDao).execute(note);
    }
    public void update(Note note){
        new UpdateNoteAsynctask(noteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsynctask(noteDao).execute(note);

    }
    public void deleteAllNotes(){
        new DeleteAllNotesAsynctask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsynctask extends AsyncTask<Note,Void,Void>{
      private NoteDao noteDao;
      private InsertNoteAsynctask(NoteDao noteDao){
          this.noteDao=noteDao;
      }
        @Override
        protected Void doInBackground(Note... notes) {
          noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsynctask extends AsyncTask<Note,Void,Void>{
      private NoteDao noteDao;
      private DeleteNoteAsynctask(NoteDao noteDao){
          this.noteDao=noteDao;
      }
        @Override
        protected Void doInBackground(Note... notes) {
          noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsynctask extends AsyncTask<Note,Void,Void>{
      private NoteDao noteDao;
      private UpdateNoteAsynctask(NoteDao noteDao){
          this.noteDao=noteDao;
      }
        @Override
        protected Void doInBackground(Note... notes) {
          noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsynctask extends AsyncTask<Void,Void,Void>{
      private NoteDao noteDao;
      private DeleteAllNotesAsynctask(NoteDao noteDao){
          this.noteDao=noteDao;
      }
        @Override
        protected Void doInBackground(Void... voids) {
          noteDao.deleteAllNotes();
            return null;
        }
    }
}
