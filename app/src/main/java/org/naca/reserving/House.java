package org.naca.reserving;

import java.io.Serializable;

/**
 * Created by fdelgado on 8/2/18.
 */

public class House implements Serializable {

    private static final long serialVersionUID = 3809936229831543140L;


    private String mName;
    private String mLocation;
    private String mDirection;
    private String mPrice;
    private String mImage;
    private String mUsers;
    private String mDescription;

    public House(String name, String location, String direction, String price, String image,
                 String users, String description) {
        mName = name;
        mLocation = location;
        mDirection = direction;
        mPrice = price;
        mImage = image;
        mUsers = users;
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDirection() {
        return mDirection;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getImage() {
        return mImage;
    }

    public String getUsers() {
        return mUsers;
    }

    public String getmDescription() {
        return mDescription;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "House{" +
                "mName='" + mName + '\'' +
                ", mLocation='" + mLocation + '\'' +
                ", mDirection='" + mDirection + '\'' +
                ", mPrice='" + mPrice + '\'' +
                ", mImage='" + mImage + '\'' +
                ", mUsers='" + mUsers + '\'' +
                '}';
    }
}
