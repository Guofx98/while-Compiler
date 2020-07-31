public class element {
    private String no;
    private String op;
    private String num1;
    private String num2;
    private String tp;

    public element(String no,String op,String num1,String num2,String tp){
        this.no=no;
        this.op=op;
        this.num1=num1;
        this.num2=num2;
        this.tp=tp;
    }
    public String getNo() {
        return no;
    }

    public String getOp() {
        return op;
    }

    public String getNum1() {
        return num1;
    }

    public String getNum2() {
        return num2;
    }

    public String getTp() {
        return tp;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }
    public String toString(){

        return "<"+no+">  ("+op+", "+num2+", "+num1+", "+tp+")";
    }
}
