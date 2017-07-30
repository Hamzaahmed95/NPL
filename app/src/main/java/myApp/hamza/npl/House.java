package myApp.hamza.npl;

/**
 * Created by Hamza Ahmed on 20-Jul-17.
 */

public class House {
    private String username;
    private String favPlayer;
    private String favTeam;
    private String house;

    public House(String Username,String FavePlayer,String FavTeam,String House){
        username=Username;
        favPlayer=FavePlayer;
        favTeam=FavTeam;
        house=House;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFavPlayer() {
        return favPlayer;
    }

    public void setFavPlayer(String favPlayer) {
        this.favPlayer = favPlayer;
    }

    public String getFavTeam() {
        return favTeam;
    }

    public void setFavTeam(String favTeam) {
        this.favTeam = favTeam;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
