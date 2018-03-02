package org.thinway.flickrbrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static org.thinway.flickrbrowser.SearchActivity.FLICKR_QUERY;

public class MainActivity extends BaseActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private List<Photo> mPhotoList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FlickrRecyclerViewAdapter mFlickrRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activateToolbar();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        mFlickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(
                MainActivity.this,
                new ArrayList<Photo>()
        );
        mRecyclerView.setAdapter( mFlickrRecyclerViewAdapter );

        // Capturar la información
        ProcessPhotos processPhotos = new ProcessPhotos(
                "android, snapshot",
                true
        );
        processPhotos.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if ( mFlickrRecyclerViewAdapter != null ){
            SharedPreferences sharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(
                            getApplicationContext()
                    );
            String query = getSavedPreferenceData(FLICKR_QUERY);
            if ( query.length() > 0) {
                ProcessPhotos processPhotos =
                        new ProcessPhotos(query, true);
                processPhotos.execute();
            }
        }
    }

    private String getSavedPreferenceData(String flickrQuery) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                        getApplicationContext()
                );

        return sharedPreferences.getString(flickrQuery, "sharapova");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if( id == R.id.menu_settings) {
            return true;
        }

        if( id == R.id.menu_search ) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ProcessPhotos extends GetFlickrJsonData {

        public ProcessPhotos(String searchCriteria, boolean matchAll) {
            super(searchCriteria, matchAll);
        }

        @Override
        public void execute() {
            super.execute();
            ProcessData processData = new ProcessData();
            processData.execute();

        }

        public class ProcessData extends DownloadJsonData {

            @Override
            protected void onPostExecute(String webData) {
                super.onPostExecute(webData);
                mFlickrRecyclerViewAdapter.loadNewData(getPhotos());
            }
        }
    }
}
