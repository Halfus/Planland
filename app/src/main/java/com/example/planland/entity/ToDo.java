package com.example.planland.entity;
import java.util.Calendar;
import java.util.Date;

public class ToDo {
    private int Id;
    private Date date;
    private String Description;
    private int Rank;
    //private int Outcome; //0-Fail; 1-Success; 2-Undecided
    private boolean isFinished;
    private boolean isPaused;
    private boolean isReminder;


    /**
     * Empty Constructor
     */
    public ToDo() {}

    /** Minimum constructor
     * @param date Deadline for the task
     * @param description The description of the task
     */
    public ToDo( Date date, String description) {
        this.date = validateDate(date);
        Description = description;
        //Outcome =2;
        isFinished=false;
        isPaused=false;
        isReminder=true;
    }

    /** Full constructor
     * @param id
     * @param date Deadline for the task
     * @param description The description of the task
     * @param rank The importance of the task
     * @param isPaused Toggle for taking into account current task
     * @param isReminder Toggle for reminders on given task
     */
    public ToDo(int id, Date date, String description, int rank, boolean isPaused, boolean isReminder) {
        Id = id;
        this.date = validateDate(date);
        Description = description;
        Rank = rank;
        //Outcome =2;
        isFinished=false;
        this.isPaused = isPaused;
        this.isReminder = isReminder;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getDate() {
        return date;
    }


    //Should be used in case previous tasks require changes, not advised for statistical accuracy
    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

//    public int getOutcome() {
//        return Outcome;
//    }
//
//    public void setOutcome(int Outcome) {
//        if(Outcome>=0 && Outcome<=2)
//            this.Outcome = Outcome;
//    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isReminder() {
        return isReminder;
    }

    public void setReminder(boolean reminder) {
        isReminder = reminder;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "Id=" + Id +
                ", date=" + date +
                ", Description='" + Description + '\'' +
                ", Rank=" + Rank +
                ", isPaused=" + isPaused +
                ", isReminder=" + isReminder +
                '}';
    }

    /** Method ensuring Task cannot be created with an invalid date
     * @param toCheck Date object that requires validation
     * @return Either the date passed as a parameter or current day if the parameter is before or the same as the current date
     */
    public Date validateDate(Date toCheck){
        Date currentTime = Calendar.getInstance().getTime();
        if(toCheck.after(currentTime))
            return toCheck;
        else
            return currentTime;
    }
    public boolean isFutureDate(Date toCheck){
        Date currentTime = Calendar.getInstance().getTime();
        return toCheck.after(currentTime);
    }
}
