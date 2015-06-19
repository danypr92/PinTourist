package gamification.pintourist.pintourist;

import android.media.Image;

/**
 * Created by Marco on 11/06/2015.
 */
public class Sfida {

    private Image immagineSfida;

    private Domanda domande [][];

    public Sfida(Domanda [][] domande, Image immagine) {

        this.domande = domande;
        this.immagineSfida = immagine;
    }

    public void setDomandeLivello1(Domanda a){
        int i = getIndex(this.domande[0]);
        if (i == -1) throw new domandePieneException("Già sono state inserite 3 domande di livello 1 per questo pin");
        else this.domande[0][i] = a;
    }

    public void setDomandeLivello2(Domanda a){
        int i = getIndex(this.domande[1]);
        if (i == -1) throw new domandePieneException("Già sono state inserite 3 domande di livello 2 per questo pin");
        else this.domande[1][i] = a;
    }

    public void setDomandeLivello3(Domanda a){
        int i = getIndex(this.domande[2]);
        if (i == -1) throw new domandePieneException("Già sono state inserite 3 domande di livello 3 per questo pin");
        else this.domande[2][i] = a;
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
