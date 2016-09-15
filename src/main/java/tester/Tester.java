/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import javax.persistence.Persistence;

public class Tester {
    
    public static void main(String[] args) {
        /* The exam preperation assignment doesn't require me to test, but this
        is where I would do that.
        
        In order for this project to be runnable we need to use valueholderinterfaces
        is something we have not learned about
        */
        String path = "com.mycompany_exam-prep-jp1_jar_1.0PU";
        Persistence.generateSchema(path, null);
        
    }
    
}
