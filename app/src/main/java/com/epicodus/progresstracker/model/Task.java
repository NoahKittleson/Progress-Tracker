package com.epicodus.progresstracker.model;

import org.parceler.Parcel;

/**
 * Created by Guest on 7/19/16.
 */

@Parcel
public class Task {
    private String name;
    private String description;
    private int goal;
    private int current;
    private boolean isDone;
    private String pushId;

    public Task() {}

    public Task(String name, String description, int goal) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.current = 0;
        this.isDone = false;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getGoal() {
        return goal;
    }

    public int getCurrent() {
        return current;
    }

    public void addCurrent(int num) {
        this.current += num;
        if (this.current >= goal) {
            setDone();
            this.current = goal;
        }
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String id) {
        this.pushId = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }


}
