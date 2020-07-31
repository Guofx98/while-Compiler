import com.sun.deploy.security.SelectableSecurityManager;
import sun.font.DelegatingShape;

import java.util.ArrayList;
import java.util.HashMap;

public class YuFa {
    public ArrayList<String> arraylist;
    public HashMap<String,Integer> all;
    public boolean a=true;
    public String message="success";
    public YuFa(ArrayList<String> list,HashMap<String,Integer>all){
        arraylist=list;
        this.all=all;
        S();
    }
    public void cut(){
            arraylist.remove(0);
    }

    public void error() {
        System.out.println("error!");
        a=false;
    }
    public void S(){
        if(arraylist.get(0).equals("while")){
            cut();
            if(arraylist.get(0).equals("(")){
                cut();
                A();
                if(arraylist.get(0).equals(")")){
                    cut();
                    if(arraylist.get(0).equals("{")){
                        cut();
                        B();
                    }
                    else {
                        error();
                        message="缺失“}”";
                    }
                }
                else {
                    error();
                    message="缺失“)”";
                }
            }
            else{
                error();
                message="缺失“(”";
            }
        }
        else {
            error();
            message="非while句型";
        }

    }
    public void A(){
        C();
        D();
        C();
    }
    public void D(){
        if(arraylist.get(0).equals(">")||arraylist.get(0).equals("<")||arraylist.get(0).equals("==")||arraylist.get(0).equals(">=")||arraylist.get(0).equals("<=")){
            cut();
        }
        else {
            error();
            message="判断符错误";
        }
    }
    public void C(){
        E();
        G();
    }
    public void E(){
        F();
        H();
    }
    public void G(){
        if(arraylist.size()>0&&arraylist.get(0).equals("+")){
            cut();
            E();
            G();
        }
        else if (arraylist.size()>0&&arraylist.get(0).equals("-")){
            cut();
            E();
            G();
        }
    }
    public void H(){
        if(arraylist.size()>0&&arraylist.get(0).equals("*")){
            cut();
            F();
            H();
        }
        else  if(arraylist.size()>0&&arraylist.get(0).equals("/")){
            cut();
            F();
            H();
        }
    }
    public void F(){
        if(arraylist.size()>0&&arraylist.get(0).equals("(")){
            cut();
            C();
            if (arraylist.size()>0&&arraylist.get(0).equals(")"))
                cut();
            else {
                error();
                message="缺失“)";
            }
        }
        else if(arraylist.size()>0&&(all.get(arraylist.get(0))==2||all.get(arraylist.get(0))==1)){
            cut();
        }
        else {
            error();
            message="标识符或常量错误";
        }
    }
    public void B(){
        if(arraylist.size()>0&&all.get(arraylist.get(0))==2){
            cut();
            if (arraylist.size()>0&&arraylist.get(0).equals(":=")){
                cut();
                C();
                if (arraylist.size()>0&&arraylist.get(0).equals(";")){
                    cut();
                    B();
                }
                else{
                    error();
                    message="缺失“;”";
                }
            }
            else {
                error();
                message="赋值符错误";
            }
        }
    }
}
