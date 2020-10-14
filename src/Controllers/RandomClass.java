package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomClass {
//    public String getRandomElement(List<String> list){
//        Random random=new Random();
//        if(list.size()==1){
//            return list.get(0);
//        }
//        return list.get(random.nextInt(list.size()));
//
//    }

    public String getRandomTableForOneOp(List<String> list){
        Collections.shuffle(list);
        return list.get(0);
    }
    public String getRandomOneElement(List<String> list){
        Collections.shuffle(list);
        for(int i=0;i<list.size();i++){
        System.out.println("hier we are in getRandomOneElement:"+list.get(i));
        }
        System.out.println("Was ist dein Problem?:"+list.get(0));
        return list.get(0);
    }
    public List<String> getRandomTableForTwoOp(List<String> list){
        Collections.shuffle(list);
        List<String> list1=new ArrayList<>();
        list1.add(list.get(0));
        list1.add(list.get(1));
        return list1;
    }

    public List<String> getThreeDifferentElement(List<String> list){
        Collections.shuffle(list);
        List<String> list1=new ArrayList<>();
        list1.add(list.get(0));
        list1.add(list.get(1));
        list1.add(list.get(2));
        return list1;
    }

    int number;
    public int getrandomColumn(int NumberOfColumn){
        List<Integer> list=new ArrayList<>();
        for(int i=1;i<=NumberOfColumn;i++){
            list.add(i);
        }
        Collections.shuffle(list);
        return list.get(0);

//        Random dice=new Random();
//        if( NumberOfColumn==1){
//            return number=1;
//        }
//        if( NumberOfColumn==2){
//            return number=1+dice.nextInt(2);
//        }
//        if (NumberOfColumn==3){
//            return number=1+dice.nextInt(3);
//        }
//        if (NumberOfColumn==4){
//            return number=1+dice.nextInt(4);
//        }
//        if (NumberOfColumn==5){
//            return number=1+dice.nextInt(5);
//        }
//        if (NumberOfColumn==6){
//            return number=1+dice.nextInt(6);
//        }
//        if (NumberOfColumn==7){
//            return number=1+dice.nextInt(7);
//        }
//        if (NumberOfColumn==8){
//            return number=1+dice.nextInt(8);
//        }
//        return number;
    }
}
