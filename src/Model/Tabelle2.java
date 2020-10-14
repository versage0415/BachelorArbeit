package Model;

public class Tabelle2 {
    private String Header1, Header2;

    public Tabelle2(String header1, String header2) {
        this.Header1 = header1;
        this.Header2 = header2;
    }

    public String getHeader1() {
        return Header1;
    }

    public void setHeader1(String header1) {
        Header1 = header1;
    }

    public String getHeader2() {
        return Header2;
    }

    public void setHeader2(String header2) {
        Header2 = header2;
    }
    public void setHeader(int i,String header) {
        if(i==1){
        Header1 = header;}
        if(i==2){
            Header2 = header;}
    }
    public String getHeader(int i) {
        if(i==1){
            return Header1;
            }
        if(i==2){
            return Header2;
           }
        return null;
    }
}
