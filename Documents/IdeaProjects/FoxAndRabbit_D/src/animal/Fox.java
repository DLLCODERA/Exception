package animal;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import cell.Cell;

public class Fox extends Animal implements Cell{
    public Fox(){ //对于每个狐狸生命信息是大致相同的，因而这里可以不写。若一些每个个体都不一致的信息就需要写出.
        super(20, 4);
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        int alpha = (int)((1 - getAgepercent())*255);
        g.setColor(new Color(255, 0, 0, alpha));
        g.fillRect(x, y, size, size);
    }

    @Override
    public Animal breed() {
        Animal ret = null;
        if (isBreedable() && Math.random() < 0.05){ //成年狐狸百分之5繁殖
            ret = new Fox();
        }
        return ret;
    }

    @Override
    public String toString() {
        return "Fox"+super.toString();
    }

    @Override
    public Animal feed(ArrayList<Animal> neighbour) { //反回被吃的rabbit
        Animal ret = null;
        if (Math.random() < 0.3){
            ret = neighbour.get((int)(Math.random()*neighbour.size()));
            longerlife(2);
        }
        return ret;
    }
}
