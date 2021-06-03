import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Minimization {
    public static int noOfState = 0,noOfFinalState=0,noOfInput = 0;
    public static int finalState[];
    public static void Minimize(State q[],int size){
        int m[][]=new int[size][size];
        int c=0;
        for(int i=0;i<noOfFinalState;i++){
            for(int j=0;j<size;j++){
                m[finalState[i]][j]=-1;
                m[j][finalState[i]]=-1;
            }
        }
        for(int i=1;i<size;i++){
            for (int j = 0; j < i; j++){
                System.out.print(m[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("output");
        do{
            c=0;
            for(int i=1;i<size;i++){
                if(q[i].finalState!=1) {
                    for (int j = 0; j < i; j++) {
                        if(q[j].finalState!=1){
                            for(int k=0;k<noOfInput;k++){
                                if(m[i][j]!=-1&&q[i].ns[k]!=q[j].ns[k]&&(q[i].finalState==0||q[j].finalState==0)&&(q[q[i].ns[k]].finalState==0||q[q[j].ns[k]].finalState==0)){
                                    if(m[q[i].ns[k]][q[j].ns[k]]==-1||m[q[j].ns[k]][q[i].ns[k]]==-1){
                                        m[i][j]=-1;
                                        c++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }while(c>0);
        for(int i=1;i<size;i++){
            for (int j = 0; j < i; j++){
                System.out.print(m[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("Minimized States are");

        for (int j = 0; j < size-1; j++){
            for(int i=j+1;i<size;i++){
                if(m[i][j]==0){
                    q[i].minimizedState=q[j].minimizedState="["+j+","+i+"]";
                    q[j].status=1;
                }
            }
            if(q[j].minimizedState==""){
                q[j].minimizedState=Integer.toString(j);
                q[j].status=1;
            }
        }
        if(q[noOfState-1].minimizedState==""){
            q[noOfState-1].minimizedState=Integer.toString(noOfState-1);
            q[noOfState-1].status=1;
        }
        q[finalState[0]].minimizedState=Arrays.toString(finalState);
        q[finalState[0]].status=1;
        for(int i=0;i<noOfState;i++){
            for(int j=0;j<noOfInput;j++){
                if(q[i].status==1){
                    System.out.println(q[i].minimizedState+"\t"+q[i].input[j]+"\t"+q[q[i].ns[j]].minimizedState);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        int f;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        /* Number of input Symbol */
        System.out.println("Enter the No of Input");
        try {
            noOfInput=Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Number of state */
        System.out.println("Enter the No of State");
        try {
            noOfState=Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Number of FinalState */
        System.out.println("Enter the No of Final State");
        try {
            noOfFinalState=Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        State q[]=new State[noOfState];
        /* enter input Symbol */
        int input[]=new int[noOfInput];
        System.out.println("Enter the Input Symbol");
        for(int i=0;i<noOfInput;i++){
            try {
                input[i]=Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /* enter State Transition*/
        for(int i=0;i<noOfState;i++){
            int ns[]=new int[noOfInput];
            for(int j=0;j<noOfInput;j++){
                System.out.println("Enter the "+i+ "'s Next State for input "+input[j]);
                try {
                    ns[j]=Integer.parseInt(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            q[i]=new State(noOfInput,input,ns);
        }
        /* enter Final State */
        System.out.println("Enter the final State");
        finalState=new int[noOfFinalState];
        for (int i=0;i<noOfFinalState;i++){
            try {
                f=Integer.parseInt(br.readLine());
                if(f<noOfState&&f>=0){
                    q[f].finalState=1;
                    finalState[i]=f;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Minimize(q,noOfState);
    }
}
