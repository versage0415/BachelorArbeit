package Model;

public class Tabelle8 {
    private String Header1;
    private String Header2;
    private String Header3;
    private String Header4;
    private String Header5;
    private String Header6;
    private String Header7;
    private String Header8;

    public Tabelle8(String header1, String header2, String header3, String header4, String header5, String header6, String header7, String header8) {
        this.Header1 = header1;
        this.Header2 = header2;
        this.Header3 = header3;
        this.Header4 = header4;
        this.Header5 = header5;
        this.Header6 = header6;
        this.Header7 = header7;
        this.Header8 = header8;
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

    public String getHeader4() {
        return Header4;
    }

    public void setHeader4(String header4) {
        Header4 = header4;
    }

    public String getHeader5() {
        return Header5;
    }

    public void setHeader5(String header5) {
        Header5 = header5;
    }

    public String getHeader6() {
        return Header6;
    }

    public void setHeader6(String header6) {
        Header6 = header6;
    }

    public String getHeader7() {
        return Header7;
    }

    public void setHeader7(String header7) {
        Header7 = header7;
    }

    public String getHeader8() {
        return Header8;
    }

    public void setHeader8(String header8) {
        Header8 = header8;
    }

    public void setHeader(int i,String header) {
        if(i==1){
            Header1 = header;}
        if(i==2){
            Header2 = header;}
        if(i==3){
            Header3 = header;}
        if(i==4){
            Header4 = header;}
        if(i==5){
            Header5 = header;}
        if(i==6){
            Header6 = header;}
        if(i==7){
            Header7 = header;}
        if(i==8){
            Header8 = header;}
    }
}
