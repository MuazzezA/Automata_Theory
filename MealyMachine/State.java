
package mealymachine;

/**
 *
 * @author Muazzez
 */
public class State {
    
    private String name;
    String[] input, output;
    int index;
    State[] nextState;
    int nextCount = 0;
            
    public State(String name, int index) {
        this.name = name;
        this.index = index;
    }
    
    public String getName(){
        return name;
    }
    
    public String[] getInput(){
        return input;
    }
    
    public String[] getOutput(){
        return output;
    }
    
    public int getIndex(){
        return index;
    }
    
    public void setIO(String input, String output){
        this.input = input.trim().split(",");
        this.output = output.trim().split(",");
    }
    
    public void setNextState(State[] nextState){
        this.nextState = nextState;
    }
    
    public void printState(){
        System.out.println("\nState Name : " + name + "\nInputs Outputs");
        for(int i = 0; i < nextState.length ; i++){
            System.out.print(i + " : "+ input[i]
                    + " / "+ output[i] 
                    +" -> " + nextState[i].name+ "\n");
        }
    }
    
    public boolean isEmpty(){ 
        if(nextState == null)
            return true;
        return false;
    }
    
    public int setNextStateSize(int size){
        if(isEmpty()){
            nextCount = size;
            nextState = new State[size];
            input = new String[size];
            output = new String[size];
            return 1;
        }
        return 0;
    }
}
