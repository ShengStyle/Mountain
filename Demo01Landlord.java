package cn.lixusheng.day04.Demo02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
    双列集合斗地主：
    斗地主，并对每个玩家的牌进行大小排序
    1.准备牌
    2.洗牌
    3.发牌
    4.排序牌
    5.看牌
 */
public class Demo01Landlord {
    public static void main(String[] args) {
        /*
            1.准备牌：
            a、创建花色和点数集合，用来存储花色和点数
            b、创建一个Map集合，key是大小排序数，value是牌，以保证牌的大小
            c、创建一个集合，用来存储key，方便洗牌
            d、根据大小规则，先把大王和小王放入Map集合的0,1号元素
            e、依次放入剩余的牌
         */
        List<String> color = List.of("♠", "♥", "♣", "♦");
        List<String> number = List.of("2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3");
        HashMap<Integer,String> puke=new HashMap<>();
        //因为要洗牌时使用，所以存放索引值必须是list集合
        List<Integer> list=new ArrayList<>();
        //初始化key值，也就是Map集合的key和list集合的元素
        int index=0;
        //把大王添加到集合，key为0,同时把index添加到list集合
        puke.put(index,"大王");
        list.add(index);
        //依次添加小王，此时index要+1
        index++;
        puke.put(index,"小王");
        list.add(index);
        index++;
        //遍历花色和点数集合，依次添加剩余的牌
        for (String numbers : number) {
            for (String colour : color) {
                puke.put(index,colour+numbers);
                list.add(index);
                index++;
            }
        }
        /*
            2.洗牌：
            把list集合洗乱，通过list集合去发牌
         */
        Collections.shuffle(list);
        /*
            3.发牌
            a、创建4个集合，三个玩家，一个底牌
            b、遍历list依次发给底牌和玩家（先处理底牌，索引大于51的牌给底牌）
         */
        ArrayList<Integer> p1=new ArrayList<>();
        ArrayList<Integer> p2=new ArrayList<>();
        ArrayList<Integer> p3=new ArrayList<>();
        ArrayList<Integer> di=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Integer num = list.get(i);
            if (i>=51){
                di.add(num);
            }else if (i%3==0){
                p1.add(num);
            }else if (i%3==1){
                p2.add(num);
            }else if (i%3==2){
                p3.add(num);
            }
        }
        /*
            4.排序牌
         */
        Collections.sort(p1);
        Collections.sort(p2);
        Collections.sort(p3);
        Collections.sort(di);
        //调用方法，看牌
        showPuke("周润发",p1,puke);
        showPuke("刘德华",p2,puke);
        showPuke("周星驰",p3,puke);
        showPuke("底牌",di,puke);
    }
    /*
        5.看牌
        定义一个看牌的方法：
        根据list集合发给每一个玩家的key值，从puke集合中找到value
     */
    public static void showPuke(String name, List<Integer> list,HashMap<Integer,String> puke){
        System.out.print(name+":");
        for (Integer number : list) {
            String pukes = puke.get(number);
            System.out.print(pukes+" ");
        }
        System.out.println();
    }

}
