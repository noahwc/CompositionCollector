package com.noahwc.compositioncollector;

public class NoteContent {

    /** This class is a simple data structure designed to hold the data of a single note.
      * It acts as an intermediary between the database and the text displayed in the single note view.
     */

    private String title;
    private String location;
    private String time;
    private String weather;
    private String gear;
    private String description;

    /**
     * Creates a blank object.
     */

    NoteContent(){
        this.title = "";
        this.location = "";
        this.time = "";
        this.weather = "";
        this.description = "";
        this.gear = "";
    }

    /**
     * @param title The content of the title field.
     * @param location The content of the location field.
     * @param time The content of the time field.
     * @param weather The content of the weather field.
     * @param gear The content of the gear field.
     * @param description The content of the description field.
     */

    NoteContent(String title, String location, String time, String weather, String gear, String description){
        this.title = title;
        this.location = location;
        this.time = time;
        this.weather = weather;
        this.description = description;
        this.gear = gear;
    }

    /**
     * Takes the fields of the instance and converts them into an array of strings.
     * @return An array of strings containing the fields of the instance. The fields in the array are in the following order: title, location, time, weather, gear, and description.
     */

    public String[] makeArray(){
        String[] arr = {title, location, time, weather, gear, description};
        return arr;
    }

    /**
     * Resets all fields.
     * @param title New title to set.
     * @param location New location to set.
     * @param time New time to set.
     * @param weather New weather conditions to set.
     * @param gear New gear to set.
     * @param description New description to set.
     */

    public void setAll(String title, String location, String time, String weather, String gear, String description){
        this.setTitle(title);
        this.setLocation(location);
        this.setTime(time);
        this.setWeather(weather);
        this.setDescription(description);
        this.setGear(gear);
    }

    /**
     * Take the contents of a string array and map them to fields. The fields in the array are in the following order: title, location, time, weather, gear, and description.
     * @param arr The array of strings to map.
     */
    public void fromArray(String[] arr){
        this.setAll(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
    }

    /**
     * Get the title field.
     * @return The title field.
     */

    public String getTitle() {
        return title;
    }

    /**
     * Set the title field.
     * @param title The new title to set.
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the location field.
     * @return The location field.
     */

    public String getLocation() {
        return location;
    }

    /**
     * Set the location field.
     * @param location The new location to set.
     */

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the time field.
     * @return The time field.
     */

    public String getTime() {
        return time;
    }

    /**
     * Set the time field.
     * @param time The new time to set.
     */

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get the weather field.
     * @return The weather field.
     */

    public String getWeather() {
        return weather;
    }

    /**
     * Set the weather field.
     * @param weather The new weather to set.
     */

    public void setWeather(String weather) {
        this.weather = weather;
    }

    /**
     * Get the gear field.
     * @return The gear field.
     */

    public String getGear() {
        return gear;
    }

    /**
     * Set gear field.
     * @param gear The new gear to set.
     */

    public void setGear(String gear) {
        this.gear = gear;
    }

    /**
     * Get the description field.
     * @return The description field.
     */

    public String getDescription() {
        return description;
    }

    /**
     * Set the description field.
     * @param description The new description to set.
     */

    public void setDescription(String description) {
        this.description = description;
    }

}
