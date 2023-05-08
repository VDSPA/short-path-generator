package team.smd.vdsp.utils;

import  java.util.Stack;
import java.util.LinkedList;
public class  Dijstra{
    private  int  v_size=0;//��ĸ���
    private  int  start=0;//������
    private  int  adjMatrix[][];
     //���·������
    private int  []shortest;
     //�жϸõ�����·���Ƿ����
    private boolean[] visited;
     //�����洢���·���ϵ�ǰ��
    private int[] pre;
    public   Dijstra(){   }
    //���캯��������ͼ�����
    public     Dijstra(int[][] Matrix,int  start){
        this.start=start;
        this.v_size=Matrix.length;
        this.adjMatrix=new int[v_size][Matrix[0].length];
        for(int i=0;i<Matrix.length;i++){
            for(int  j=0;j<Matrix[i].length;j++){
                        this.adjMatrix[i][j]=Matrix[i][j];
            }
        }
        shortest=new int[v_size];
        visited=new boolean[v_size];
        pre=new int[v_size];

       for(int i=0;i<Matrix.length;i++){
        visited[i]=false;
        shortest[i]=Integer.MAX_VALUE;
        pre[i]=-1;
       }

 
    }
    //�Ծ�����г�ʼ��
    public   void  initialize(){
        for(int i=0;i<adjMatrix.length;i++){
            for(int j=0;j<adjMatrix.length;j++){
                if((i!=j)&&(adjMatrix[i][j]==0)){
                    adjMatrix[i][j]=Integer.MAX_VALUE;
                }
            }
        }
    }
    //Ѱ�����·��
    public  LinkedList<step>  shortest(){
        LinkedList<step> queue = new LinkedList<>();
        step  s;
        initialize();
       //��ʼ��Դ�ڵ�
       shortest[start] = 0;
       visited[start] = true;
       Target[] t=new Target[1];
       t[0]=new Target("node",""+start);
       s=new step("settle",t);
       queue.offer(s);
       //���������Ե�������е�
       for(int i=0;i<v_size;i++){
        shortest[i]=adjMatrix[start][i];
        
        
        if(adjMatrix[start][i]!=Integer.MAX_VALUE){
            //����������������ֱ�Ӵﵽ�ĵ㣬��setter
            //traverse edge start:i
            t[0]=new Target("edge",start+":"+i);
            s=new step("traverse",t);
            queue.offer(s);
            //traverse  node  i
            t[0]=new Target("node",""+i);
            s=new step("traverse",t);
            queue.offer(s);
           
            pre[i]=start;

            
        }
       }
       
       pre[start]=-1;
       int min=Integer.MAX_VALUE;
       int min_index=start;

    for(int x=0;x<v_size-1;x++){
        for(int i=0;i<v_size;i++){
            //����ѭ�����еĵ㣬�����ҵ���ûȷ�����·������dist��С�Ķ���v1
			//��min����ʾ��̾��룬��min_index����ʾv1������
            
            if(visited[i]==true)
            {
                continue;
            }
                
            else{
                
                if(shortest[i]<min){
                    min=shortest[i];
                    min_index=i;
                }
            }

        }
        

        visited[min_index]=true;
        //settle   index min_index
        t[0]=new Target("node",""+min_index);
        s=new step("settle",t);
        queue.offer(s);
        //��������ڽ���v1�ıߣ�����v1���ڽӶ���
        for(int j=0;j<v_size;j++){
            
            if(visited[j]==false &&(adjMatrix[min_index][j]!=Integer.MAX_VALUE)){
                //traverse edge  min_index��j
                t[0]=new Target("edge",min_index+":"+j);
                s=new step("traverse",t);
                queue.offer(s);
                //traverse  index  j
                t[0]=new Target("node",""+j);
                s=new step("traverse",t);
                queue.offer(s);
                if((min+adjMatrix[min_index][j])<shortest[j]){
                    shortest[j]=min+adjMatrix[min_index][j];
                    pre[j]=min_index;
                    
                }
                
                
            }
        }
        min=Integer.MAX_VALUE;
        
    }
           //showInfo();
           //ȥ���������ظ���Ԫ��
           for (int i = 0; i < queue.size(); i++) {
            if(i==queue.size()-1)
            break;
            else{
                if( queue.get(i).equals( queue.get(i+1)) ){
                    queue.remove(i);
                }
            }
           
        }
        
           
           return  queue;
       
    }

    //����������·��
    public  String  onePath(int  index){
        String  path="";
        Stack<Integer>  s1=new  Stack<Integer>();
        while(index!=-1){
            s1.push(index);
            index=findPre(index);
        }
        while(s1.empty()!=true){
            path+=s1.pop()+",";
        }
        return  path;

    }
    //����pre���ҵ�ĳ���������·���϶�Ӧ��ǰ��
    int  findPre(int index){
        return  pre[index];
    }
    //չʾ���߱����Ϣ���������ڲ�����
    public  void  showInfo(){
        System.out.println("Shortest path:");
       
        for(int i=0;i<v_size;i++){           
                System.out.print(shortest[i]+" ");
        }
        System.out.println(" ");
        System.out.println("Visited:");
        for(int i=0;i<v_size;i++){
                System.out.print(visited[i]+" ");
        }
        System.out.println(" ");
        System.out.println("Pre:");
        for(int i=0;i<v_size;i++){
            System.out.print(pre[i]+" ");
        }
        System.out.println(" ");

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
        //DirectedWattsStrogatzGenerator  arr=new  DirectedWattsStrogatzGenerator();
        //int[][]  arrMatrix=arr.generate(12,2,0.5);
        //DirectedWattsStrogatzGenerator.publicPrintAdjMatrix(arrMatrix);
        int[][]  arrMatrix={{0,1,0,3,0},{0,0,2,4,0},{0,0,0,0,1},{0,0,0,0,1},{0,0,0,0,0}};
        Dijstra  d1= new Dijstra(arrMatrix,0);
        d1.shortest();
        d1.printMatrix();
        for(int i=0;i<5;i++){
            String  path=d1.onePath(i);
            //LinkedList<step>  step=d1.shortest();
            System.out.println("INDEX"+i+"'s path:"+path);
        }
        

    }
}
