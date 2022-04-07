package TP1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

class UneFenetre extends JFrame implements ActionListener
{
    UnMobile sonMobile,sonMobile2;Thread laTache;Thread laTache2;JButton btnSuspend;JButton btnSuspend2;
    private final int LARG=800, HAUT=750;
    private boolean isRunning =true,isRunning2 =true;

    private ArrayList<UnMobile> mesMobiles = new ArrayList<>();
    private HashMap<JButton,Integer> mesBoutons = new HashMap<>();
    private ArrayList<Thread> mesThreads = new ArrayList<>();
    private ArrayList<Boolean> areRunning = new ArrayList<>();

    private final int NOMBRE_DE_MOBILES = 100;

    JButton bouton;
    UnMobile mobile;
    public UneFenetre()
    {
        setLayout(new GridLayout(NOMBRE_DE_MOBILES,2));

        for(int i = 0 ; i < NOMBRE_DE_MOBILES ; i++){
            mobile = new UnMobile(LARG/2,HAUT/NOMBRE_DE_MOBILES);
            mesMobiles.add(mobile);
            bouton = new JButton("Suspendre");
            bouton.addActionListener(this);
            mesBoutons.put(bouton,i);
            areRunning.add(true);
            Thread thread = new Thread(mobile);
            mesThreads.add(thread);
            thread.start();


            this.add(mobile);
            this.add(bouton);
        }

        setSize(LARG,HAUT);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {        //Implémentation de la méthode provoquée à l'action
        Integer id = mesBoutons.get(e.getSource());

        if(areRunning.get(id)){ //Reprise
            mesThreads.get(id).suspend();
            ((JButton)e.getSource()).setText("Reprendre");
        }
        else{ //Pause
            mesThreads.get(id).resume();
            ((JButton)e.getSource()).setText("Suspendre");
        }
        areRunning.set(id,!(Boolean)areRunning.get(id)); //On inverse la variable de fonctionnement
    }
}
