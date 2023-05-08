package team.smd.vdsp.utils;

import  java.util.Stack;
import java.util.LinkedList;
public class universal {
    public  static  void  main(String[] args){
        //matrix for test
        int[][]  arrMatrix={{0,1,0,3,0},{0,0,2,4,0},{0,0,0,0,1},{0,0,0,0,1},{0,0,0,0,0}};
        //start node
        int start=0;
        //创建一个step队列，用来记录traverse和settle的过程
        LinkedList<step>  queue=new  LinkedList<step>();
        /*------------------ TEST FOR DIJSTRA-------------------------*/
        Dijstra  d1= new Dijstra(arrMatrix,start);
        //打印矩阵
        d1.printMatrix();
        queue=d1.shortest();
        //输出队列中的值
        while(queue.isEmpty()!=true){
            step  head = queue.poll();
            head.showStep();
           }
        //allPath用来保存全部的路径
        String[] allPath = new String[arrMatrix.length];
        for(int i=0;i<allPath.length;i++){
            allPath[i]=d1.onePath(i);
        }
        //打印起点到所有点的最短路径
        for(int  i=0;i<allPath.length;i++){
            System.out.println("index"+i+"'s path:"+allPath[i]);
        }

        /*-------------------TEST FOR DFS ---------------------*/
         DFS  d2=new  DFS(arrMatrix,start);
         //打印矩阵
        d2.printMatrix();
        for(int i=0;i<arrMatrix[0].length;i++){
            queue.addAll(d2.shortest(start, i));
            
        }
        //输出step队列中的值,即整个过程
        while(queue.isEmpty()!=true){
            step  head = queue.poll();
            head.showStep();
           }
        
        //展示起点和其他点之间的最短路径和最短路径的长度
        System.out.println("Show the shortest path between start node and other node:");
        for(int  i=0;i<arrMatrix[0].length;i++){
            if(i!=start){
                d2.shortest(start, i);
                d2.showFinalInfo();
            }
        }
        
       
    }
}



