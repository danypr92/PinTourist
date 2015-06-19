package gamification.pintourist.pintourist;

/**
 * Created by Marco on 11/06/2015.
 */
public class Indizio {

    private String [] indizi;

    public Indizio(String [] arrayIndizi){
        indizi = new String [3];
    }

    public String getIndizi(int numeroIndizio) {
        return indizi[numeroIndizio];
    }
}
