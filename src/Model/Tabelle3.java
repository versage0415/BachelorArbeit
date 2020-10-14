package Model;

public class Tabelle3 {
    private String Header1;
    private String Header2;
    private String Header3;

    public Tabelle3(String header1, String header2, String header3) {
        this.Header1 = header1;
        this.Header2 = header2 ;
        this.Header3 = header3;
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

    public String getHeader3() {
        return Header3;
    }

    public void setHeader3(String header3) {
        Header3 = header3;
    }

    public void setHeader(int i,String header) {
        if(i==1){
            Header1 = header;}
        if(i==2){
            Header2 = header;}
        if(i==3){
            Header3 = header;}
    }
}
