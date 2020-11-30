package de.ppi.here.socialmedia.bo;

import java.util.Date;

public class Post {
    private String content;
    private String author;
    private Date timestamp;

    public Post(final String content, final String author, final Date timestamp) {
        this.content = content;
        this.author = author;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }
}
