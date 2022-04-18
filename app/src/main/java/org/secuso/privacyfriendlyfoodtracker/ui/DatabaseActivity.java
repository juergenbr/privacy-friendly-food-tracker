/*
This file is part of Privacy friendly food tracker.

Privacy friendly food tracker is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Privacy friendly food tracker is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Privacy friendly food tracker.  If not, see <https://www.gnu.org/licenses/>.
*/

package org.secuso.privacyfriendlyfoodtracker.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.secuso.privacyfriendlyfoodtracker.R;
import org.secuso.privacyfriendlyfoodtracker.database.ApplicationDatabase;
import org.secuso.privacyfriendlyfoodtracker.database.Product;
import org.secuso.privacyfriendlyfoodtracker.ui.adapter.DatabaseFacade;
import org.secuso.privacyfriendlyfoodtracker.ui.helper.BaseActivity;

import java.util.List;

/**
 * Displays an "about" page
 *
 * @author Jürgen Breitenbaumer, juergenbr
 */
public class DatabaseActivity extends BaseActivity {

    private DatabaseFacade databaseFacade;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_database);
        Button buttonExp = (Button) findViewById(R.id.export_button);
        Button buttonImp = (Button) findViewById(R.id.import_button);
    }

    protected int getNavigationDrawerID() {
        return R.id.nav_database;
    }

    public void onClickExportBtn(View v) {
        try {
            List<Product> products = ApplicationDatabase.getInstance(this.getApplicationContext()).getConsumedEntriesAndProductDao().getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickImportBtn(View v) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

