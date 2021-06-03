public class State {
    int status,finalState;
    int ns[];
    int input[];
    String minimizedState;
    State(int noOfInput, int input[], int ns[]){
        this.status=0;
        this.ns=ns;
        this.input=input;
        this.finalState=0;
        minimizedState=new String();
        minimizedState="";
    }
}
