package com.noahwc.compositioncollector;

public class NoteContent {

    private String title;
    private String location;
    private String time;
    private String weather;
    private String gear;
    private String description;

    NoteContent(){
    }

    NoteContent(String title, String location, String time, String weather, String gear, String description){
        this.title = title;
        this.location = location;
        this.time = time;
        this.weather = weather;
        this.description = description;
        this.gear = gear;
    }

    public String[] makeArray(){
        String[] arr = {title, location, time, weather, gear, description};
        return arr;
    }

    public void setAll(String title, String location, String time, String weather, String gear, String description){
        this.setTitle(title);
        this.setLocation(location);
        this.setTime(time);
        this.setWeather(weather);
        this.setDescription(description);
        this.setGear(gear);
    }

    public void fromArray(String[] arr){
        this.setAll(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
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

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
