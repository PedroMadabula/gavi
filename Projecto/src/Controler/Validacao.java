package Controler;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Nesta classe constam diferentes metodos de validacao.
 * Em especial, a validacao do BI e do E-mail do usuario.
 * No caso de existirem excepcoes o programa nao sera interrompido, 
 * ate que o usuario digite a informacao correcta.
 */

public class Validacao implements Serializable{

    

    public Validacao() {
        
    }

    public boolean validarBI(String bi) {
        if(bi == null) return false;
                if ((bi.length() != 13)) {
                    return  false;
                }else{
                    for (int i = 0; i < bi.length()-1; i++) {
                        if(!Character.isDigit(bi.charAt(i))){
                            return  false;
                        }
                    }
                    if(!Character.isLetter(bi.charAt(12))){
                        return  false;
                    }
                return  true;
                }
    }
    
    /* Para validar o E-mail do usuario, foram utilizadas classes da biblioteca java.util*
     * Tais metodos sao usados para verificar uma certa sequencia de caracteres.
     */ 
    public boolean validarEmail(String res) {
        Matcher matcher;
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        try{
        matcher = emailPat.matcher(res);
        return matcher.find();
        }catch(NullPointerException io){
            return false;
        }
    }

    public boolean validarString(String name, int b1,int b2) {
        if ((name==null) || (name.equals("") ||(!name.matches("^[a-zA-Z]*$"))) || (name.length() < b1 || name.length() > b2)){
            return false;
        }else{
            return true;
        }
    }
    public boolean validarRua(String name, int b1,int b2) {
      
        if ((name==null) || (name.equals("")) || (name.length() < b1 || name.length() > b2)){
            
            return false;
        }else{
            return true;
        }
    }
    public boolean validarLong(long x, long a, long b) {
            if (x < a || x > b){
                return false;
            }else{
                return true;
            }
    }

    public double validarDouble(String msg1, double a, double b) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double d = 0;
        boolean error = true;

        do {
            do {
                try {
                    System.out.println(msg1);
                    d = Double.parseDouble(br.readLine());
                    error=false;
                } catch (NumberFormatException e1) {
                    System.out.println("ERRO! O valor introduzido nao e' real  \nPor favor tente novamente com um valor real\n______________________________________________________");
                } catch (IOException e2) {
                    System.out.println(e2.getMessage());
                }
            } while (error);
            System.out.println(d);
            if (d < a || d > b)
                System.out.println("ERRO! Valor fora do intervalo "+a+" a "+b+" \nPor favor tente novamente\n______________________________________________________");
        } while (d < a || d > b);
        return d;
    }

    public boolean validarInt(String xee, int a, int b) {
        try{
            int x=Integer.parseInt(xee);
            if (x < a || x > b){
                return false;
            }else{
                return true;
            }
        }catch(NumberFormatException s){
            return false;
        }
    }

    public boolean validarSenha(String xee, int a, int b) {
        try{
            int x=Integer.parseInt(xee);
            if (x < a || x > b){
                return false;
            }else{
                return true;
            }
        }catch(NumberFormatException s){
            return false;
        }
    }

    public boolean validarPreco(String xee, int a, int b) {
         try{
            double x=Double.parseDouble(xee);
            if (x < a || x > 999999999){
                return false;
            }else{
                return true;
            }
        }catch(NumberFormatException s){
            return false;
        }
    }
}