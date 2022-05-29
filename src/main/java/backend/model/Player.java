package backend.model;

import backend.model.playerDetails.Position;
import backend.model.playerDetails.PreferredFoot;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerID;

    @Column
    private String name;

    @Column
    private Integer kitNumber;

    @Column
    private Integer age;

    @Column
    private String nationality;

    @Enumerated(EnumType.STRING)
    private PreferredFoot preferredFoot;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Column
    private Integer goalsScored;

    @Column
    private Integer assistsProvided;

    public Player(String name,
                  Integer kitNumber,
                  Integer age,
                  String nationality,
                  PreferredFoot preferredFoot,
                  Position position,
                  Integer goalsScored,
                  Integer assistsProvided) {
        this.name = name;
        this.kitNumber = kitNumber;
        this.age = age;
        this.nationality = nationality;
        this.preferredFoot = preferredFoot;
        this.position = position;
        this.goalsScored = goalsScored;
        this.assistsProvided = assistsProvided;
    }

    public Player() {}

    public Long getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKitNumber() {
        return kitNumber;
    }

    public void setKitNumber(Integer kitNumber) {
        this.kitNumber = kitNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public PreferredFoot getPreferredFoot() {
        return preferredFoot;
    }

    public void setPreferredFoot(PreferredFoot preferredFoot) {
        this.preferredFoot = preferredFoot;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(Integer goalsScored) {
        this.goalsScored = goalsScored;
    }

    public Integer getAssistsProvided() {
        return assistsProvided;
    }

    public void setAssistsProvided(Integer assistsProvided) {
        this.assistsProvided = assistsProvided;
    }
}
