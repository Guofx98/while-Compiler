import sun.swing.BakedArrayList;

import java.io.*;
import java.util.*;

public class Bianyi {
    private Queue<element> queue=new LinkedList<element>();
    private ArrayList<String> list=new ArrayList();
    private HashMap<String,Integer>all;
    private HashMap<String,Integer> hashMap=new HashMap<>();
    public Stack<String> num=new Stack<String>();
    public Stack<String> op=new Stack<String>();
    private CiFa ciFa;
    private YuFa yuFa;
    public Bianyi(){
        String keyword[]={"while"};
        String symbol[]={"+","-","*","/",">","<=",":=","<","==",">="};
        ciFa=new CiFa(keyword,symbol);
        this.all=ciFa.all;
        hashMap.put(":=",0);
        hashMap.put(">",1);
        hashMap.put("<",1);
        hashMap.put(">=",1);
        hashMap.put("<=",1);
        hashMap.put("==",1);
        hashMap.put("+",2);
        hashMap.put("-",2);
        hashMap.put("*",3);
        hashMap.put("/",3);
        hashMap.put("%",3);
    }
    public String[][] getCiFa(){
        String [][] str=new String[this.list.size()][2];
        for(int i=0;i<this.list.size();i++){
            str[i][0]=this.list.get(i);
            String ss="";
            switch(all.get(list.get(i))){
                case 0: ss="关键字";break;
                case 1: ss="常量";break;
                case 2: ss="标识符";break;
                case 3: ss="操作符";break;
                case 4: ss="界符";break;
                case 5: ss="分隔符";break;
            }
            str[i][1]=ss;
        }
        return str;
    }
    public String getYufa(){
        return yuFa.message;
    }
    public Queue<element> yuyi(String filename) throws IOException {
        File file=new File(filename);
        FileInputStream fis =new FileInputStream(file);
        InputStreamReader is =new InputStreamReader(fis);
        BufferedReader bfr=new BufferedReader(is);
        String s="";
        String str="";
        while((s=bfr.readLine())!=null){
            str+=s;
        }
        ArrayList<String> alist=ciFa.analyse(str);
        this.list=(ArrayList<String>) alist.clone();
        yuFa=new YuFa(alist,ciFa.all);
        if(yuFa.a==true){
            int n=2;
            int no=1;
            int count=1;
            while(n<list.size()&&!list.get(n).equals(")")){
                if(all.get(list.get(n))==1||all.get(list.get(n))==2){
                    num.push(list.get(n));
                    n++;
                }
                else {
                    if(op.size()==0){
                        op.push(list.get(n));
                        n++;
                    }
                    else {
                        if(hashMap.get(list.get(n))>hashMap.get(op.peek())){
                            op.push(list.get(n));
                            n++;
                        }
                        else {
                            while(num.size()>0&&op.size()>0&&hashMap.get(list.get(n))<=hashMap.get(op.peek())) {
                                String num1 = num.pop();
                                String num2 = num.pop();
                                String sign = op.pop();
                                element e= new element((count++) + "", sign, num1, num2, "T" + no);
                                queue.offer(e);
                                num.push("T" + no++);
                            }
                            op.push(list.get(n));
                            n++;
                        }
                    }
                }
            }
            while(op.size()>0){
                if(hashMap.get(op.peek())==1){
                    element e= new element((count++) + "", "j"+op.pop(), num.pop(), num.pop(), "<"+(count+1)+">");
                    queue.offer(e);
                    element e1= new element((count++) + "", "j", "_ ", "_ ", "?");
                    queue.offer(e1);
                }
                else{
                    element e= new element((count++) + "", op.pop(), num.pop(), num.pop(), "T"+no);
                    queue.offer(e);
                    num.push("T"+no);
                    no++;
                }
            }
            n+=2;
            int t=n;
            int k=0;
            while(!list.get(n).equals("}")){
                if(list.get(n).equals(";"))
                    k++;
                n++;
            }
            n=t;
            for(int i=0;i<k;i++){
                while(n<list.size()&&all.get(list.get(n))!=0&&!list.get(n).equals(";"))
                {
                    if(all.get(list.get(n))==1||all.get(list.get(n))==2){
                        num.push(list.get(n));
                        n++;
                    }
                    else {
                        if(op.size()>0){
                            if(hashMap.get(list.get(n))>hashMap.get(op.peek())){
                                op.push(list.get(n));
                                n++;
                            }
                            else{
                                while(num.size()>0&&op.size()>0&&hashMap.get(list.get(n))<=hashMap.get(op.peek())) {
                                    String num1 = num.pop();
                                    String num2 = num.pop();
                                    String sign = op.pop();
                                    element e= new element((count++) + "", sign, num1, num2, "T"+no);
                                    queue.offer(e);
                                    num.push("T" + no++);
                                }
                                op.push(list.get(n));
                                n++;
                            }
                        }
                        else{
                            op.push(list.get(n));
                            n++;
                        }
                    }
                }
                while(op.size()>0)
                {
                    if(op.peek().equals(":=")){
                        element e= new element((count++) + "", op.pop(), num.pop(), "_ ", num.pop());
                        queue.offer(e);
                    }
                    else{
                        element e= new element((count++) + "", op.pop(), num.pop(), num.pop(), "T"+no);
                        queue.offer(e);
                        num.push("T"+no);
                        no++;
                    }
                }
                if (list.get(n).equals(";")){
                    n++;
                }
            }
            element e=new element((count++) + "", "j", "_ ", "_ ", "<1>");
            queue.offer(e);
        }
        else
            System.out.println("语法错误！");
        return queue;
    }
    public static void main(String args[]) throws IOException {
        Bianyi bianyi=new Bianyi();
        bianyi.yuyi("test.txt");
    }
}
