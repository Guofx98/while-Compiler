import java.util.ArrayList;
import java.util.HashMap;

public class CiFa {
    private HashMap<String,Integer> keyword=new HashMap<String,Integer>();
    private HashMap<String,Integer>symbol=new HashMap<String,Integer>();
    public HashMap<String,Integer>all=new HashMap<String,Integer>();
    public CiFa(String keyword[] ,String symbol[]){
        for(int i=0;i<keyword.length;i++){
            this.keyword.put(keyword[i],1);
        }
        for(int i=0;i<symbol.length;i++){
            this.symbol.put(symbol[i],1);
        }
    }
    public  ArrayList<String> analyse(String line){
        ArrayList<String > list=new ArrayList<>();
                int n=0;
                while (n<line.length()){
                    if(line.charAt(n)>='A'&&line.charAt(n)<='z') {
                        String token="";
                        while (line.charAt(n) >= 'A' && line.charAt(n) <= 'z' || line.charAt(n)>='0'&&line.charAt(n)<='9') {
                            token += line.charAt(n);
                            n++;
                        }
                        if(keyword.get(token)!=null){
                            all.put(token,0);//关键字
                            list.add(token);
                        }
                        else {
                            all.put(token,2);//标识符
                            list.add(token);
                        }
                    }
                    else if(line.charAt(n)>='0'&&line.charAt(n)<='9'){
                        String token="";
                        while (line.charAt(n)>='0'&&line.charAt(n)<='9'){
                            token+=line.charAt(n);
                            n++;
                        }
                        all.put(token,1);//常量
                        list.add(token);
                    }
                    else if(line.charAt(n)!=' ') {
                        if(line.charAt(n)=='('||line.charAt(n)==')'||line.charAt(n)=='{'||line.charAt(n)=='}'){
                            all.put(line.charAt(n)+"",4); //界符
                            list.add(line.charAt(n)+"");
                            n++;
                        }
                        else if(line.charAt(n)==';'){
                            all.put(line.charAt(n)+"",5); //分隔符
                            list.add(line.charAt(n)+"");
                            n++;
                        }
                        else {
                            String token = "";
                            while (line.charAt(n) < '0' || line.charAt(n) > '9' && line.charAt(n) < 'A' || line.charAt(n) > 'z') {
                                token += line.charAt(n);
                                n++;
                                if (n < line.length() && symbol.get(token + line.charAt(n)) != null) {
                                    all.put(token + line.charAt(n), 3);
                                    list.add(token + line.charAt(n));
                                    n++;
                                    break;
                                }
                                if (symbol.get(token) != null) {
                                    all.put(token, 3);//符号
                                    list.add(token);
                                    break;
                                }
                                if (n >= line.length())
                                    break;
                            }
                        }
                    }
                    else{
                        n++;
                    }

                }
            return list;
        }
}
