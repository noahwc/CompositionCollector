package com.example.android.compositioncollector;

public class NoteContent {

    private String title;
    private String location;
    private String time;
    private String weather;
    private String description;

    NoteContent(String title, String location, String time, String weather, String description){
        this.title = title;
        this.location = location;
        this.time = time;
        this.weather = weather;
        this.description = description;
    }

    public void setAll(String title, String location, String time, String weather, String description){
        this.setTitle(title);
        this.setLocation(location);
        this.setTime(time);
        this.setWeather(weather);
        this.setDescription(description);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
