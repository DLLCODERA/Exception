package FoxAndRabbit;

import field.Field;
import field.View;
import field.Location;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import animal.Animal;
import animal.Fox;
import animal.Rabbit;
import cell.Cell;

public class FoxAndRabbit {
    private Field thefield;
    private View theview;
    private JFrame frame;

    public FoxAndRabbit(int size){
        thefield = new Field(size, size);
        for (int row = 0; row < thefield.getHeight(); row++){
            for (int col = 0; col < thefield.getWidth(); col++){
                double probability = Math.random();
                if (probability < 0.05){
                    thefield.place(row, col, new Fox());
                }
                else if (probability < 0.15){
                    thefield.place(row, col, new Rabbit());
                }
            }
        }
        theview = new View(thefield);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //xx
        frame.setResizable(false);  //尺寸大小不能改
        frame.setTitle("小狐狸与大白兔");
        frame.add(theview);
        //加个开关
        JButton btnStep = new JButton("ten steps");
        frame.add(btnStep, BorderLayout.NORTH);  //layout manege 保证了最低效果.

        //消息机制
        btnStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //注册，反转控制。
                System.out.println("按下成昆");
                for (int i = 0; i < 10; i++){
                    step();
                    frame.repaint();
                }
            }
        });

        frame.pack();  //
        frame.setVisible(true);
    }

    public void step(){
        for (int row = 0; row < thefield.getHeight(); row++){
            for (int col = 0; col < thefield.getWidth(); col++){
                Cell cell = thefield.get(row, col);
                if (cell != null){
                    Animal animal = (Animal)cell;
                    animal.grow();
                    if (animal.isAlive()){
                        //move
                        Location loc = animal.move(thefield.getFreeNeighbour(row, col));
                        if (loc != null){
                            thefield.move(row, col, loc);
                        }
                        //eat animal.eat()
                        if (animal instanceof Fox){ //检测这个animal 是不是Fox的子类
                            Cell[] neighbour = thefield.getNeighbour(row, col);
                            ArrayList<Animal> listRabbit = new ArrayList<Animal>();
                            for (Cell an: neighbour){
                                if (an instanceof Rabbit){
                                    listRabbit.add((Rabbit)an);
                                }
                            }
                            if (!listRabbit.isEmpty()){
                                Animal fed = animal.feed(listRabbit);
                                if (fed != null){
                                    thefield.remove((Cell)fed);
                                }
                            }
                        }
                        //breed
                        Animal baby = animal.breed();
                        if (baby != null){
                            thefield.placeRandomAdj(row, col, (Cell)baby);
                        }
                    }
                    else {
                        thefield.remove(row, col);
                    }
                }
            }
        }
    }

    public void start(int steps){
        for (int i = 0; i < steps; i++){
            step();
            theview.repaint();
            try{
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FoxAndRabbit far = new FoxAndRabbit(50);
        far.start(500 );
    }
}
