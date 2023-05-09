package team.smd.vdsp.utils;

import java.util.Arrays;
import java.util.LinkedList;
class Target{
    private String  role;//node or edge
    private String  id;//id
    Target(){
        role="";
        id="";
    }
    Target(String  role,String  id){
        this.role=role;
        this.id=id;
    }
    public boolean  equals(Target  otherTarget){
        if((otherTarget.id.equals(this.id))&&(otherTarget.role.equals(this.role))){
            return  true;
        }
        else{
            return  false;
        }
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Target target = (Target) obj;
        if (this.id != target.id) return false;
        return this.role.equals(target.role);
    }

    public  void  setRole(String r){
        role =r;
    }
    public  void  setId(String i){
        id=i;
    }
    public  String  getRole(){
        return  role;
    }
    public  String  getId(){
        return  id;
    }
    public   void setTarget(String r,String i){
        role=r;
        id=i;
    }
    public void showTarget(){
        System.out.print("Role:"+role+" ");
        System.out.print("Id:"+id+" ");
        System.out.println("");
    }
}
public class step {
  
    private String  type;//traverse  or  settle  or  reset
    private Target[]  targets;//Update one or more nodes'state simultaneously
    step(){
        type="";
        targets=null;
    }
    step(String type,Target[]  targets){
        this.type=type;
        this.targets= Arrays.copyOf(targets,targets.length);
    }
    //重写equal方法，用于比较两个step对象是否相同
    public  boolean  equals(step  otherStep){
        if((otherStep.type.equals(this.type))&&(Arrays.equals(this.getTargets(),otherStep.getTargets()))){
            return  true;
            
        }
        else{
            return  false;
        }
    }
    
    public  void  setType(String  t){
        type=t;
    }
   
    public  String getType(){
        return  type;
    }
    public void  setTargets(Target[]  targets){
        this.targets=targets;
    }
    public Target[]  getTargets(){
        return  targets;
    }
    public  void  showStep(){
        System.out.println("Type:"+type+" ");
        for(int i=0;i<this.targets.length;i++){
            this.targets[i].showTarget();
        }
        System.out.println("-----------------------------");
    }

    public  static  void  main(String[] args){
        /*step internal test*/
        Target[] t=new Target[2];
        t[0]=new Target("node",""+1);
        step s=new step("traverse",t);
        t[1]=new Target("node",""+3);
        step s2=new step("traverse",t);
     
        s2.showStep();
        
    

    }

}
