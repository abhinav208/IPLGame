/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_UTILITY;

import IPL_BEANS.MatchCollection;
import IPL_LOGIC.MatchCollectionLogic;

/**
 *
 * @author Abhinav Kumar
 */
public class testClass {
    public static void main(String args[]){
        //String returnMessage = "All Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done well";
        //String returnMessage = "All Done wellAll Done wellAll Done wellProblem in updating transaction, but user points updatedAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done well";
        String returnMessage = "All Done wellAll Done wellUser Points not updatedAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done wellAll Done well";
        if (returnMessage.toLowerCase().contains("points")){
            System.out.println("Issue in NBU point update...!!");
        }
        else {
            System.out.println("NBU Point updated Successfully");
        }
        
    }
}
