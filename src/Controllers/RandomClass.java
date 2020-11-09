package Controllers;

import java.util.List;
import java.util.Random;

public class RandomClass {
    public String getRandomElement(List<String> list){
        Random random=new Random();
        if(list.size()==1){
            return list.get(0);
        }
        return list.get(random.nextInt(list.size()));


    }
    int number;
    public int getrandomColumn(int NumberOfColumn){
        Random dice=new Random();
        if( NumberOfColumn==1){
            return number=1;
        }
        if( NumberOfColumn==2){
            return number=1+dice.nextInt(2);
        }
        if (NumberOfColumn==3){
            return number=1+dice.nextInt(3);
        }
        if (NumberOfColumn==4){
            return number=1+dice.nextInt(4);
        }
        if (NumberOfColumn==5){
            return number=1+dice.nextInt(5);
        }
        if (NumberOfColumn==6){
            return number=1+dice.nextInt(6);
        }
        if (NumberOfColumn==7){
            return number=1+dice.nextInt(7);
        }
        if (NumberOfColumn==8){
            return number=1+dice.nextInt(8);
        }
        return number;
    }
}
