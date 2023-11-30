package edu.birzeit.notes;

public class Task {

    private int taskID;
    private String taskTitle;
    private String taskTxt;
    private boolean taskStatus;

    public Task() {

    }

    public Task(String taskTitle, String taskTxt, boolean taskStatus) {
        this.taskTitle = taskTitle;
        this.taskTxt = taskTxt;
        this.taskStatus = taskStatus;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskTxt() {
        return taskTxt;
    }

    public void setTaskTxt(String taskTxt) {
        this.taskTxt = taskTxt;
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }




}