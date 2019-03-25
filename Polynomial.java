import java.util.*;
/**
 * A class polynomial that takes the use of liked list to perform different 
 * activities with the data of the polynomial like getting the cofficient ,
 * removing the specific term from the polynomial,represent the derivative of the 
 * given polynomial and also do suming and prodeuct og two polynoials
 *
 * @author Bisrat Asefaw
 * @version 11/18/2018
 */
public class Polynomial{   

    private PolyNode front;
    private PolyNode current;
    private int term;
    /**
     * Construct a Polynomial based on a string which contains a series of numbers 
     * separated by whitespace. The numbers are always in pairs: coefficient followed
     * by exponent.
     * 
     * @param s Sstring representation of the polynomial
     * @return null if the string s is empety
     */
    public Polynomial(String s){
        if(s.equals(null)||s==""){
            front=null;
            term=0;
            return ;
        }
        String[] string=s.split(" ");
        for(int i=0;i<string.length;i+=2){

            double coefficient=Double.parseDouble(string[i]);
            int exponent =Integer.parseInt(string[i+1]);
            if(front==null){
                front=new PolyNode(coefficient,exponent,front);
                current=front;
                term++;
            }
            else{
                current.next=new PolyNode(coefficient,exponent,current.next);
                term++;
                current=current.next;
            }
        }
    }

    /**
     * Construct a Polynomial that is a duplicate (a deep copy) of the one
     * provided without modifying p.
     * 
     * @param p the polynomial to be deep copied
     * @return null if the given polynomial is null
     */
    public Polynomial(Polynomial p){
        if(p==null){
            return;
        }
        
        PolyNode temp=p.front;
        term=p.term;
        while(temp!=null){

            double x=temp.coefficient;
            int y=temp.exponent;
            if(front==null){
                front=new PolyNode(x,y,front);
                current=front;
            }else{
                current.next=new PolyNode(x,y,current.next);
                current=current.next;
            } 
            temp=temp.next;  
        }
    }

    /**
     * method that Returns the number of terms in this Polynomial.
     * 
     * @return term the number of terms in the given polynomial
     */
    public int terms(){
        return term;
    }

    /**
     * Method that Adds a new term to this Polynomial if there is not already a term with the specified exponent. 
     * If a term with the specified exponent already exists, update its coefficient by summing 
     * it with the new one.this method makes sure not to have two terms with the same exponent.
     * 
     * @ throws IllegalArgumentException when the user try to add a term with zero coefficient
     * @ param coef the cofficient of the term to be added
     * @ param exp the exponent of the term to be added
     */
    public void addTerm(double coef, int exp){
        if(coef==0){
            throw new IllegalArgumentException("you're not adding term");
        }

        PolyNode temp=front;
        if(front!=null){
            if(front.exponent==exp){

                front=new PolyNode(coef+front.coefficient,exp,temp.next);
                return;
            }
            else if(front.exponent<exp){
                front=new PolyNode(coef,exp,front);
                term++;
                return;
            }
        }else if(front==null){
            front=new PolyNode(coef,exp,front);            
            temp=front;
        }

        while(temp.next!=null){
            if(temp.next.exponent==exp){

                temp.next=new PolyNode(coef+temp.next.coefficient,exp,temp.next.next);
                return;
            }else if(temp.next.exponent<exp){
                temp.next=new PolyNode(coef,exp,temp.next);
                term++;
                return;
            }
            temp=temp.next;
        }
        temp.next=new PolyNode(coef,exp,temp.next);
    }

    /**
     * Method delete term with the specified exponent exists in this Polynomial, 
     * delete that term and return its coefficient.
     * 
     *@ param exp the exponent of the term to be deleted
     *@ return coef the coefficient of the deleted term
     *@ return 0.0 if the specific term with the given exp. is not found
     */
    public double deleteTerm(int exp){
        PolyNode temp=front;
        double coef=0;

        if(front.exponent==exp){
            coef=front.coefficient;
            front=front.next;
            term--;
            return coef;
        }

        while(temp.next!=null){
            if(temp.next.exponent==exp){
                coef=temp.next.coefficient;
                term--;
                temp.next=temp.next.next;
                return coef;
            }
            temp=temp.next;
        }
        return coef;
    }

    /**
     * Method that returns the coefficient for the specified exponent;
     * return 0.0 if no such term.
     * 
     * @ param exp the exponent of the term
     * @ return coef the coefficient of the spesified term
     * @ return 0.0 if there is no term with a specified exponent
     */
    public double getCoefficient(int exp){
        PolyNode temp=front; 
        double coef=0;
        while(temp!=null){
            if(temp.exponent==exp){
                coef=temp.coefficient;                
            }
            temp=temp.next;
        }
        return coef;
    }

    /**
     * Method that returns the value of this Polynomial for the given value for the variable x.
     * or returns zero if the Polynomial is zero
     * 
     * @ param x the value of x for this polynomial
     * @ return result the result of the polynomial with a given value of x
     * @ return 0.0 if the polynomial is null
     */
    public double evaluate(double x){
        PolyNode temp=front;
        double result=0.0;
        while(temp!=null){
            result+=temp.coefficient*(Math.pow(x,temp.exponent));
            temp=temp.next;
        }
        return result;
    }

    /**
     * equals method that cheks if this polynomial if equal or not to the given polynomial
     * 
     * 
     * @ param o the given object to be compared with this polynomial
     * 
     * @ return true if they are equal 
     * @ return false is they are not equal
     */
    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }

        if(o == null || getClass()!=o.getClass()){
            return false;
        }

        Polynomial poly=(Polynomial)o;
        if(terms()!=poly.terms()){
            return false;
        }
        PolyNode temp=poly.front;
        PolyNode myTemp=front;
        while(temp!=null){
            if(myTemp.coefficient!=temp.coefficient){

                return false;
            }
            if(myTemp.exponent!=temp.exponent){
                return false;
            }
            myTemp=myTemp.next;
            temp=temp.next; 
        }
        return true;
    }

    /**
     * Method that returns the first derivative of this Polynomial which gives the power times the 
     * coefficient if the exponent is not zero
     * 
     * @ return firstDerivative the first derivative of this polynomial 
     */
    public Polynomial derivative( ){
        //Polynomial derivative=new Polynomial(this);     
        PolyNode temp=front;
        String info="";

        while(temp!=null){

            if(temp.exponent!=0){

                temp=new PolyNode(temp.exponent*temp.coefficient,temp.exponent-1,temp.next);
                info+=temp.coefficient+" "+temp.exponent+" ";
                temp=temp.next;
            }
            else{
                temp=temp.next;
                term--;
            }   
        }
        Polynomial firstDerivative = new Polynomial(info);
        return firstDerivative ;  
    }

    /**The 'toString' method that produce output that represents the Polynomial in the normal fashion. 
     * this method dispalays the string representation of this polynomial
     * and tekes care of the terms that have a coefficient of zero. If the coefficient becomes  zero 
     * (any arthematic operation like addition or multiplication), this method eliminates the term.
     * 
     * @return 0.0 if this polynomial is null
     * 
     * @return newstr3 the string representation of this polynomial
     */
    public String toString( ){
        //PolyNode temp=front;
        if(front==null){
            return "0.0";
        }
        int counter=0;
        String info="";
        PolyNode temp=front;
        while(temp!=null){
            if(temp.coefficient==0.0){
                term--;
            }
            else if(temp.coefficient>0){
                if(temp.coefficient==1){
                    if(temp.exponent==0){info+=" + "+temp.coefficient;}
                    else if(temp.exponent==1){info+=" + x";}
                    else{info+=" + x^"+temp.exponent;}
                }
                else{
                    if(temp.exponent==0){info+=" + "+temp.coefficient;}
                    else if(temp.exponent==1){info+=" + "+ temp.coefficient+"x";}
                    else{info+=" + "+ temp.coefficient+"x^"+temp.exponent;}
                    //info+="+"+temp.coefficient+"x^"+temp.exponent;
                }
                counter++;
            }else{
                if(counter==0){
                    info+="   ";
                }
                if(temp.coefficient==-1){
                    if(temp.exponent==0){
                        if(counter!=0){info+=" - "+ -1*temp.coefficient;}
                        else{info+=temp.coefficient;}
                    }
                    else if(temp.exponent==1){
                        if(counter!=0){info+=" - x";}
                        else{info+="-x";}
                    }
                    else{
                        if(counter!=0){info+=" - x^"+temp.exponent;}
                        else{info+="-x^"+temp.exponent;}
                    }
                }else{
                    if(temp.exponent==0){
                        if(counter!=0){info+=" - "+ -1*temp.coefficient;}
                        else{info+=temp.coefficient;}
                    }
                    else if(temp.exponent==1){
                        if(counter!=0){info+=" - "+-1*temp.coefficient+"x";}
                        else{info+=temp.coefficient+"x";};
                    }
                    else{
                        if(counter!=0){info+=" - "+ -1*temp.coefficient+"x^"+temp.exponent;}
                        else{info+=temp.coefficient+"x^"+temp.exponent;}
                    }

                }  
                counter++;
            }
            temp=temp.next;
        }
        String newstr1 = info.substring(0, 0) + info.substring(0 + 1);
        String newstr2 = newstr1.substring(0, 0) + newstr1.substring(0 + 1);
        String newstr3 = newstr2.substring(0, 0) + newstr2.substring(0 + 1);        
        return newstr3;
    }

    /**
     * method that uses a private recursive method to display the string representation of this polynomial
     * 
     * @ return 0.0 if this polynomial is null    
     * @ return description(temp) the call to the private ricursive method
     */
    public String description( ){
        PolyNode temp=front;
        if(front==null){
            return "0.0";
        }
        return description(temp);
    }

    /**
     * recursive method that returns the string representation of this polynomial
     * 
     * @ return temp the front of this polynomial
     */
    private String description(PolyNode temp){        
        if(temp.next==null){
            if(temp.exponent==0){
                return "constant term "+temp.coefficient;  
            }
            else{
                return "exponent "+temp.exponent+", coefficient "+temp.coefficient;
            }
        }
        else{
            return  description(temp.next) + "\nexponent "+temp.exponent+", coefficient "+temp.coefficient ;
        }
    }

    /**
     * method that displays the sum os two polynomials, this method does not change the the value
     * of the polynomial whichi are given as argument it creates a new deep copy of
     *  them and uses it to add the vales of those polynomial
     * 
     * @param a the first polynomial to be added 
     * 
     * @param b the second polynomial to be added
     * 
     * @return polySum the sum of the two polynomials a and b
     */
    public static Polynomial sum(Polynomial a, Polynomial b){
        Polynomial polySum;        
        String str="";
        if(a.terms()>b.terms()){
            polySum=new Polynomial(a);
            PolyNode temp1=a.front;
            PolyNode temp2=b.front;

            while(temp2!=null){
                polySum.addTerm(temp2.coefficient,temp2.exponent);
                temp2=temp2.next;       
            }
        }
        else{
            polySum=new Polynomial(b);
            PolyNode temp2=a.front;
            PolyNode temp1=b.front;

            while(temp2!=null){
                polySum.addTerm(temp2.coefficient,temp2.exponent);
                temp2=temp2.next;       
            }
        }
        return polySum;
    }

    /**
     * method that displays the product of the two given polynomials a and b, this method does not 
     * change the behavior or the value of it's polynomial parameters it creats a deep copy of the 
     * parameters and get the derivative
     * 
     * @param a the first polynomial to be multiplied 
     * 
     * @param b the second polynomial to be mulatiplied
     * 
     *@ return 0.0 polynomial if the either a or b or both are 0.0 polynomial, zero multipied with any number is zero
     */
    public static Polynomial product( Polynomial a, Polynomial b){
        if(a==null||b==null){
            return new Polynomial("");
        }
        Polynomial polyPro=new Polynomial(a);
        Polynomial poly=new Polynomial(b);
        Polynomial product=new Polynomial("");
        int ter=polyPro.terms();
        int ter2=poly.terms();
        String str="";
        if(ter>ter2){

            PolyNode x=polyPro.front;
            PolyNode y=poly.front;
            for(int j=0;j<1;j++){
                while(x!=null){
                    int exp=x.exponent+y.exponent;
                    str+=x.coefficient*y.coefficient+" "+ exp +" ";                    
                    x=x.next;
                }
                product=new Polynomial(str);
                y=y.next;
            }
            if(y!=null){
                for(int i=1;i<ter2;i++){ 
                    x=polyPro.front;
                    double coef=0;
                    int exp=0;
                    while(x!=null){
                        coef=x.coefficient*y.coefficient;
                        exp=x.exponent+y.exponent;
                        product.addTerm(coef,exp);
                        x=x.next;
                    }
                    y=y.next;
                }
            }
        }else{
            PolyNode x=polyPro.front;
            PolyNode y=poly.front;
            for(int j=0;j<1;j++){
                while(y!=null){
                    int z=x.exponent+y.exponent;
                    str+=x.coefficient*y.coefficient+" "+ z +" ";
                    //System.out.println(str);
                    y=y.next;
                }
                product=new Polynomial(str);
                x=x.next;
            }
            if(x!=null){
                for(int i=1;i<ter;i++){   
                    double coef=0;
                    int exp=0;
                    y=poly.front;
                    while(y!=null){
                        coef=x.coefficient*y.coefficient;
                        exp=x.exponent+y.exponent;
                        product.addTerm(coef,exp);
                        y=y.next;
                    }

                    x=x.next;
                }
            }
        }

        return product;
    }
    
    // public void arrange(){
        // PolyNode temp=front;
        
        // for(int i=0;i<term;i++){
            // PolyNode curr=temp;
            // temp=temp.next;
           // while(temp.next!=null){
               
               // if(curr.exponent<temp.exponent){
                   // PolyNode n=
                // }
                // temp=temp.next;
            // }
           // PolyNode x=
        // }
    // }
}

/** 
 * class that creats the node for the polynomial that containf the exponent , coefficient and
 * the next Polynomial it uses the the LikedList concept to Link the terms in a polynomial  
 */
class PolyNode{
    public double coefficient;
    public int exponent;
    public PolyNode next;
    /**
     * creat a node with zero exponent and coefficient and null next
     */
    public PolyNode(){
        this(0,0,null);        
    }

    /**
     * creat a polyNode with given coefficient and exponent and null Next term
     * @param coefficient the coefficient of the node to be created 
     * @param exponent the exponent of the node to be created
     */
    public PolyNode(double coefficient,int exponent){
        this(coefficient,exponent,null);        
    }

    /**
     * creat a polyNode with given coefficient and 0 exponent and null Next term
     * @param coefficient the coefficient of the node to be created 
     */
    public PolyNode(double coefficient){
        this(coefficient,0,null);
    }

    /**
     * creat a polyNode with given coefficient and  exponent and  Next term
     * @param coefficient the coefficient of the node to be created 
     * @param exponent the exponent of the node to be created
     * @param next the next term of the polynomial
     */
    public PolyNode(double coefficient,int exponent,PolyNode next){
        this.coefficient=coefficient;
        this.exponent=exponent;
        this.next=next;
    }
}
