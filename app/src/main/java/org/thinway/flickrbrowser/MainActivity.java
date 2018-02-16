package org.thinway.flickrbrowser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private List<Photo> mPhotoList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FlickrRecyclerViewAdapter mFlickrRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        ProcessPhotos processPhotos = new ProcessPhotos(
                "android, snapshot",
                true
        );
        processPhotos.execute();
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
                mFlickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(
                        MainActivity.this, getPhotos()
                        );

                mRecyclerView.setAdapter(mFlickrRecyclerViewAdapter);
            }
        }
    }
}
