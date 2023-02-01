package com.example.planland.entity;

import java.util.ArrayList;
import java.util.Iterator;

public class User {

    private int Id;
    private String Username;
    private String Password;
    private String FirstName;
    private String LastName;
    private String Email;
    private ArrayList<ToDo> FutureToDo;
    private ArrayList<ToDo> PreviousToDo;

    /**
     * Empty constructor
     */
    public User() {}


    /** No ID minimum constrictor
     * @param username String containing the username of the user
     * @param password String containing the password of the user
     */
    public User(String username, String password) {
        Username = username;
        Password = password;
        FirstName="John";
        LastName="Doe";
        Email="example11@extensiom.com";
        FutureToDo=new ArrayList<>();
        PreviousToDo=new ArrayList<>();
    }

    /** Minimum constructor
     * @param id ID of a given user (PK in database)
     * @param username String containing the username of the user
     * @param password String containing the password of the user
     */
    public User(int id, String username, String password) {
        Id = id;
        Username = username;
        Password = password;
        FirstName="John";
        LastName="Doe";
        Email="example11@extensiom.com";
        FutureToDo=new ArrayList<>();
        PreviousToDo=new ArrayList<>();
    }

    /** Constructor with contact details
     * @param id ID of a given user (PK in database)
     * @param username String containing the username of the user
     * @param password String containing the password of the user
     * @param firstName String containing the first name of the user
     * @param lastName String containing the last name of the user
     * @param email String containing the email of the user
     */
    public User(int id, String username, String password, String firstName, String lastName, String email) {
        Id = id;
        Username = username;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        FutureToDo=new ArrayList<>();
        PreviousToDo=new ArrayList<>();
    }

    /** Full constructor
     * @param id ID of a given user (PK in database)
     * @param username String containing the username of the user
     * @param password String containing the password of the user
     * @param firstName String containing the first name of the user
     * @param lastName String containing the last name of the user
     * @param email String containing the email of the user
     * @param futureToDo A list of future tasks
     * @param previousToDo A list of past tasks
     */
    public User(int id, String username, String password, String firstName, String lastName, String email, ArrayList<ToDo> futureToDo, ArrayList<ToDo> previousToDo) {
        Id = id;
        Username = username;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        FutureToDo = futureToDo;
        PreviousToDo = previousToDo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public ArrayList<ToDo> getFutureToDo() {
        return FutureToDo;
    }

    public void setFutureToDo(ArrayList<ToDo> futureToDo) {
        FutureToDo = futureToDo;
    }

    public ArrayList<ToDo> getPreviousToDo() {
        return PreviousToDo;
    }

    public void setPreviousToDo(ArrayList<ToDo> previousToDo) {
        PreviousToDo = previousToDo;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }


    /**
     * Moves completed or old tasks form Future list to Previous list using Collection and Remove
     */
    public void refreshTasks(){
        ArrayList<ToDo> found = new ArrayList<>();

        for (ToDo task:FutureToDo)
            if(task.isFinished() || !task.isFutureDate(task.getDate()))
                found.add(task);

        PreviousToDo.addAll(found);
        FutureToDo.removeAll(found);
    }

//
//      Moves completed or old tasks form Future list to Previous list using ListIterator
//
//    public void refreshTasks2(){
//
//        Iterator<ToDo> i = FutureToDo.listIterator();
//
//        while (i.hasNext()){
//            ToDo task = i.next();
//            if(task.isFinished() || !task.isFutureDate(task.getDate())){
//                PreviousToDo.add(task);
//                i.remove(); //Needs testing
//            }
//
//        }
//    }



}
