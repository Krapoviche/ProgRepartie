import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=400, HAUT=250;

    public UneFenetre()
    {
        setSize(LARG,HAUT);
        setVisible(true);
        this.sonMobile=new UnMobile(LARG,HAUT);
        Thread laTache = new Thread (sonMobile);
        this.add(this.sonMobile);
        laTache.start();
    }
}
