package prelim.backend;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;

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

    public Athlete(String name, char sex, int age, int height, int weight, String team, String NOC,
    Year year, String season, String city, String sport, String event, String medal) {}

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

    public void addEventStanding(String[] information) {
        this.event.add(information[12]);
        this.medal.add(information[13]);
    }

    @Override
    public String toString() {
        return name + ", " + sex + ", " + age + ", " + height + ", " + weight + ", " + team + ", " +
                NOC + ", " + year + ", " + season + ", " + city + ", " + sport + ", " + event + ", " + medal;
    }
}
