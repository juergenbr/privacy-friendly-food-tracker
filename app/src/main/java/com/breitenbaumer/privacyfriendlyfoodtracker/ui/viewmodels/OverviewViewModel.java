package com.breitenbaumer.privacyfriendlyfoodtracker.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.breitenbaumer.privacyfriendlyfoodtracker.ui.adapter.DatabaseEntry;
import com.breitenbaumer.privacyfriendlyfoodtracker.ui.adapter.DatabaseFacade;

import java.util.Date;
import java.util.List;

public class OverviewViewModel extends AndroidViewModel {
    private LiveData<List<DatabaseEntry>> list = new MutableLiveData<>();

    DatabaseFacade dbHelper = null;

    /**
     * @param application
     */
    public OverviewViewModel(@NonNull Application application) {
        super(application);

        try {
            dbHelper = new DatabaseFacade(application.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param day The date for which the database entries are to be fetched
     */
    public void init(Date day) {
        list = dbHelper.getConsumedEntriesForDay(day);
    }

    public LiveData<List<DatabaseEntry>> getList() {
        return list;
    }


    public void deleteEntryById(int id) {
        dbHelper.deleteEntryById(id);
    }

    public void editEntryById(int id, int amount) {
        dbHelper.editEntryById(id, amount);
    }

}
