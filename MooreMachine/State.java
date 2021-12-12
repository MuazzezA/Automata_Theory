
package moore;

/**
 *
 * @author maazez
 */
public class State {
    
    public String name;
    public String output;
    public String[] input;
    int index = -1;
    public State[] nextState;
    public int nextIndex = -1;
    
    
    public State(String name, String output, int index) {
        this.name = name;
        this.output = output;
        this.index = index;
    }
       
    public void contextState(State nextStates, String inputs){
        nextIndex++;
        nextState[nextIndex] = nextStates;
        input[nextIndex] = inputs;
    }
    
    public void printState(){
        System.out.println("\nState Name : "+name+"\nOutput : "+output+"\nNextIndexCount : "+nextIndex+"\nInputs ");
        for(int i = 0; i < nextIndex + 1; i++)
            System.out.println(input[i] + " Next State Name: " + nextState[i].name);
    }
    
    public int isEmpty(){ 
        if(nextState == null || input == null)
            return 1;
        return 0;
    }
    
    public int setNextStateSize(int size){
        if(isEmpty() == 1){
            nextState = new State[size];
            input = new String[size];
            return 1;
        }
        return 0;
    }
}
