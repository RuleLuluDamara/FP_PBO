import java.util.Comparator;

public class Scoreboard<T, U>{
    T score;
    U nama;

    public Scoreboard(T score, U nama){
        this.score = score;
        this.nama = nama;
    }

    public T getScore(){
        return score;
    }

    public U getName(){
        return nama;
    }

}