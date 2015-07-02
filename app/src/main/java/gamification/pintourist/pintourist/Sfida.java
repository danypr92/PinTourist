package gamification.pintourist.pintourist;

import android.media.Image;

/**
 * Created by Marco on 11/06/2015.

public class Sfida {

    private Image immagineSfida;

    private Domanda domande [][];

    public Sfida(Domanda [][] domande, Image immagine) {

        this.domande = domande;
        this.immagineSfida = immagine;
    }




    public void setDomandeLivello(int livello, Domanda a){
        int i = getIndex(this.domande[livello]);
        if (i == -1) throw new domandePieneException("Già sono state inserite 3 domande di livello "+livello+" per questo pin");
        else this.domande[livello][i] = a;
    }



    static class domandePieneException extends RuntimeException{
        public domandePieneException(String msg){
            super(msg);
        }
    }

    private int getIndex(Domanda[] a){
    int i = 0;
    while (a[i]!= null) i++;
    if (i == a.length -1) return -1;
    return i;
}

}
 */