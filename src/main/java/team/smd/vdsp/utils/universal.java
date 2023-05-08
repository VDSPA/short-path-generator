package team.smd.vdsp.utils;

import  java.util.Stack;
import java.util.LinkedList;
public class universal {
    public  static  void  main(String[] args){
        //matrix for test
        int[][]  arrMatrix={{0,1,0,3,0},{0,0,2,4,0},{0,0,0,0,1},{0,0,0,0,1},{0,0,0,0,0}};
        //start node
        int start=0;
        //����һ��step���У�������¼traverse��settle�Ĺ���
        LinkedList<step>  queue=new  LinkedList<step>();
        /*------------------ TEST FOR DIJSTRA-------------------------*/
        Dijstra  d1= new Dijstra(arrMatrix,start);
        //��ӡ����
        d1.printMatrix();
        queue=d1.shortest();
        //��������е�ֵ
        while(queue.isEmpty()!=true){
            step  head = queue.poll();
            head.showStep();
           }
        //allPath��������ȫ����·��
        String[] allPath = new String[arrMatrix.length];
        for(int i=0;i<allPath.length;i++){
            allPath[i]=d1.onePath(i);
        }
        //��ӡ��㵽���е�����·��
        for(int  i=0;i<allPath.length;i++){
            System.out.println("index"+i+"'s path:"+allPath[i]);
        }

        /*-------------------TEST FOR DFS ---------------------*/
         DFS  d2=new  DFS(arrMatrix,start);
         //��ӡ����
        d2.printMatrix();
        for(int i=0;i<arrMatrix[0].length;i++){
            queue.addAll(d2.shortest(start, i));
            
        }
        //���step�����е�ֵ,����������
        while(queue.isEmpty()!=true){
            step  head = queue.poll();
            head.showStep();
           }
        
        //չʾ����������֮������·�������·���ĳ���
        System.out.println("Show the shortest path between start node and other node:");
        for(int  i=0;i<arrMatrix[0].length;i++){
            if(i!=start){
                d2.shortest(start, i);
                d2.showFinalInfo();
            }
        }
        
       
    }
}



