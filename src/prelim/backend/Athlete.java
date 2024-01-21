package prelim.backend;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Athlete {
    private String name;
    private char sex;
    private int age;
    private int height;
    private int weight;
    private String team;
    private String NOC;
    private Year year;
    private String season;
    private String city;
    private String sport;
    private ArrayList<String> event;
    private ArrayList<String> medal;


    public Athlete(String name, char sex, int age, int height, int weight, String team, String NOC, Year year, String season, String city, String sport, ArrayList<String> event, ArrayList<String> medal) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.NOC = NOC;
        this.year = year;
        this.season = season;
        this.city = city;
        this.sport = sport;
        this.event = event;
        this.medal = medal;
    }

    public Athlete(String[] information) {
        event = new ArrayList<>();
        medal = new ArrayList<>();
        name = information[1];
        sex = information[2].charAt(0);
        age = Integer.parseInt(information[3]);
        height = Integer.parseInt(information[4]);
        weight = Integer.parseInt(information[5]);
        team = information[6];
        NOC = information[7];
        year = Year.parse(information[8]);
        season = information[9];
        city = information[10];
        sport = information[11];
        event.add(information[12]);
        medal.add(information[13]);
    }

    // So there aren't multiple records of an individual athlete in the hashmap,
    // we'll just collate the unique event and medal information as an arrayList and keep the other information
    public void addEventStanding(String[] information) {
        //TODO: check whether there is an athlete who plays a different sport.
        this.event.add(information[12]);
        this.medal.add(information[13]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNOC() {
        return NOC;
    }

    public void setNOC(String NOC) {
        this.NOC = NOC;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getSport() {
        return Collections.singletonList(sport);
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public ArrayList<String> getEvent() {
        return event;
    }

    public void setEvent(ArrayList<String> event) {
        this.event = event;
    }

    public ArrayList<String> getMedal() {
        return medal;
    }

    public void setMedal(ArrayList<String> medal) {
        this.medal = medal;
    }

    @Override
    public String toString() {
        return name + ", " + sex + ", " + age + ", " + height + ", " + weight + ", " + team + ", " +
                NOC + ", " + year + ", " + season + ", " + city + ", " + sport + ", " + event + ", " + medal;
    }
}
