package team.smd.vdsp.utils;
import  java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import  java.util.Arrays;
import  java.util.List;
public class DFS {
    private  int  v_size=0;//��ĸ���
    private  int  start=0;//������
    private  int  adjMatrix[][];
    private  ArrayList<Integer>  shortest=new ArrayList<>();//�����洢��ǰ��̵�·��
    private  int  shortestDis;//�����洢��ǰ���·���ĳ���
    
    public  DFS(){}
    public    DFS(int[][] Matrix,int  start){
        this.start=start;
        this.v_size=Matrix.length;
        this.adjMatrix=new int[v_size][Matrix[0].length];
        for(int i=0;i<Matrix.length;i++){
            for(int  j=0;j<Matrix[i].length;j++){
                        this.adjMatrix[i][j]=Matrix[i][j];
            }
        }
        for(int i=0;i<adjMatrix.length;i++){
            for(int j=0;j<adjMatrix.length;j++){
                if((i!=j)&&(adjMatrix[i][j]==0)){
                    adjMatrix[i][j]=Integer.MAX_VALUE;
                }
            }
        }
 
    }
    
    ////�ҵ�������֮�������·��������������С������·��
    public   LinkedList<step>  shortest(int  start,int  end){
        shortestDis=Integer.MAX_VALUE;
        boolean[]  visited=new  boolean[v_size];//��ǽڵ��Ƿ񱻷��ʹ�
        ArrayList<Integer> pathList=new ArrayList<>();//�����洢��ǰ��·��
        int[]  pathLength =new  int[v_size];
        Arrays.fill(pathLength,Integer.MAX_VALUE);
        //����㿪ʼ�������·��
        pathList.add(start);
        pathLength[start]=0;
        LinkedList<step>  queue=new LinkedList<step>();
        queue=dfsShortest(start, end, visited, pathList, pathLength);
        Target[] t=new Target[1];
        t[0]=new Target("edge","");
        step s=new step("reset",t);
        queue.offer(s);
        //ȥ���������ظ���Ԫ��
        
        for (int i = 0; i < queue.size(); i++) {
            if(i==queue.size()-1)
                break;
            else{
               if( (queue.get(i)).equals( queue.get(i+1))){
                    queue.remove(i);
                }
            }
        }
        return  queue;
    }

    public  LinkedList<step> dfsShortest(int  u,int end,boolean[] visited,ArrayList<Integer> pathList,int[] pathLength){
        //u:���
        //end:end point
        LinkedList<step> queue = new LinkedList<>();
        step  s;
        visited[u]=true;
        //traverse  node  u
        Target[] t=new Target[1];
        if(u==start){
            t[0]=new Target("node",""+start);
            s=new step("traverse",t);
            queue.offer(s);
        }
        
        if(u==end){//��ǰ�ڵ�Ϊ�յ�
            //����·�������е����·���ĳ������Ƚϣ�����ǰ·�����̣���������·��
            if(shortestDis>pathLength[end]){
                shortestDis=pathLength[end];
                shortest=new ArrayList<>(pathList);
                
            }
            visited[u]=false;//����
            return queue;
        }

        for(int  v=0;v<v_size;v++){
            if(adjMatrix[u][v]!=0 && !visited[v] &&adjMatrix[u][v]!=Integer.MAX_VALUE ){
                 
                //���ڱ�����δ�����ʹ�����������
                pathList.add(v);
                //traverse  node v
                // t[0]=new Target("node",""+u);
                // s=new step("traverse",t);
                // queue.offer(s); 
                //traverse  edge u:v
                t[0]=new Target("edge",u+":"+v);
                s=new step("traverse",t);
                queue.offer(s);
                //settle  edge u:v
                t[0]=new Target("edge",u+":"+v);
                s=new step("settle",t);
                queue.offer(s);
                //traverse  node v
                t[0]=new Target("node",""+v);
                s=new step("traverse",t);
                queue.offer(s);                
                pathLength[v]=pathLength[u]+adjMatrix[u][v];
                queue.addAll(dfsShortest(v, end, visited, pathList, pathLength));
                pathList.remove(pathList.size()-1);//����
                pathLength[v]=Integer.MAX_VALUE;
            }
        }
        visited[u]=false;//����
        

        return  queue;
    }

    //����Ѱ�����յ�·����Ϣ
    public  void  showFinalInfo(){
        System.out.println("SHOW FINAL INFO");
        System.out.println("Length:"+shortestDis);
        for(int  i=0;i<shortest.size();i++){
            
            System.out.print(shortest.get(i)+" -> ");
        }
        
        System.out.println("");

    }

    //չʾ�м���Ϣ
    private  void  showInfo(boolean[] visited,ArrayList<Integer> pathList,int[] pathLength){
        for(int  i=0;i<visited.length;i++){
            System.out.print("visited:"+visited[i]+" ");
        }
        System.out.println("");
        for(int  i=0;i<pathList.size();i++){
            System.out.print("pathList:"+pathList.get(i)+" ");
        }
        System.out.println("");
        for(int  i=0;i<pathLength.length;i++){
            System.out.println("pathLength"+pathLength[i]+" ");
        }
        System.out.println("");
    }



    //��ӡ����
    public   void  printMatrix(){
        for(int i=0;i<adjMatrix.length;i++){
            for(int j=0;j<adjMatrix[i].length;j++){
                if(adjMatrix[i][j]==Integer.MAX_VALUE){
                    System.out.printf("%5s","MAX");
                }
                else{
                    System.out.printf("%5s",adjMatrix[i][j]+"");
                }
               
            }
            System.out.println();
        }

    }
    
    public  static void main(String[] args){

        /*For DFS internal test*/
        int[][]  arrMatrix={{0,1,0,3,0},{0,0,2,4,0},{0,0,0,0,1},{0,0,0,0,1},{0,0,0,0,0}};
        int  start=0;//���
        DFS   d2=new  DFS(arrMatrix,start);
        System.out.println("Show matrix:");
        d2.printMatrix();
        //չʾ����������֮������·�������·���ĳ���
        System.out.println("Show the shortest path between start node and other node:");
        for(int  i=0;i<d2.v_size;i++){
            if(i!=start){
                d2.shortest(start, i);
                d2.showFinalInfo();
            }
        }
        //չʾ����ĳ���յ�֮��Ѱ�����·���Ĺ���
        System.out.println("Show every step:");        
        LinkedList<step>  queue=new  LinkedList<step>();
        queue=d2.shortest(start, 3);
        queue.addAll(d2.shortest(start,2));
        //��������е�ֵ
        while(queue.isEmpty()!=true){
            step  head = queue.poll();
            head.showStep();
           }

    }
}
