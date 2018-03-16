package org.naca.reserving;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fdelgado on 8/2/18.
 */

public class GetReservingJsonData extends GetRawData {

    private static final String LOG_TAG = GetReservingJsonData.class.getSimpleName();

    private List<House> mHouses;
    private Uri mDestinationUri;

    public GetReservingJsonData(String searchCriteria, boolean matchAll) {
        super(null);
        createAndUpdateUri(searchCriteria, matchAll);
        mHouses = new ArrayList<>();
    }

    public List<House> getPhotos() {
        return mHouses;
    }

    private boolean createAndUpdateUri(String searchCriteria, boolean matchAll) {


        final String RESERVING_URL = "http://reserving.herokuapp.com/api/houses";

        mDestinationUri = Uri.parse(RESERVING_URL).buildUpon()
                .build();

        return mDestinationUri != null;
    }

    private void processResult() {
        final String NAME = "name";
        final String DESCRIPTION = "description";
        final String DIRECTION = "direction";
        final String LOCATION = "location";
        final String IMAGES = "images";
        final String PRICE = "price_user_night";
        final String USERS = "max_users_house";
        final String IMG_LINK = "image_url";

        if (getDownloadStatus() != DownloadStatus.OK) {
            Log.e(LOG_TAG, "No se ha descargado el JSON");
            return;
        }

        try {

            JSONArray jsonData = new JSONArray(getData());
            JSONArray itemsArray = jsonData;


            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject jsonPhoto = itemsArray.getJSONObject(i);
                String name = jsonPhoto.getString(NAME);
                String location = jsonPhoto.getString(LOCATION);
                String direction = jsonPhoto.getString(DIRECTION);
                String price = jsonPhoto.getString(PRICE);
                String image = "";

                String users = jsonPhoto.getString(USERS);
                String description = jsonPhoto.getString(DESCRIPTION);

                try {
                    JSONArray images = jsonPhoto.getJSONArray(IMAGES);
                    for (int j = 0; j < images.length(); j++) {
                        JSONObject imagesArray = images.getJSONObject(j);
                        image = imagesArray.getString(IMG_LINK);
                    }

                } catch (JSONException e) {
                }


                House house = new House(name,location,direction,price,image,users,description);
                mHouses.add(house);
            }

            for (House house : mHouses) {
                Log.d(LOG_TAG, "House: " + house.toString());
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "No se puede crear el objeto JSON");
            e.printStackTrace();
        }
    }

    public void execute() {
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG, "Build Uri: " + mDestinationUri.toString());
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public class DownloadJsonData extends DownloadRawData {

        @Override
        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();
        }

        @Override
        protected String doInBackground(String... params) {
            String[] par = {mDestinationUri.toString()};

            return super.doInBackground(par);
        }
    }


}