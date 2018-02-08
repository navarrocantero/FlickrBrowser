package org.thinway.flickrbrowser;

/**
 * Created by fdelgado on 8/2/18.
 */

public class Photo {

    private String mTitle;
    private String mLink;
    private String mAuthor;
    private String mAuthorId;
    private String mImage;
    private String mTags;

    public Photo(String title, String link, String author, String authorId, String image, String tags) {
        mTitle = title;
        mLink = link;
        mAuthor = author;
        mAuthorId = authorId;
        mImage = image;
        mTags = tags;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getAuthorId() {
        return mAuthorId;
    }

    public String getImage() {
        return mImage;
    }

    public String getTags() {
        return mTags;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mAuthorId='" + mAuthorId + '\'' +
                ", mImage='" + mImage + '\'' +
                ", mTags='" + mTags + '\'' +
                '}';
    }
}
