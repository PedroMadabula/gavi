package Controler;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controle_EscolherFoto extends JFrame{
    private final JFileChooser jFileChooser1;
   
    public Controle_EscolherFoto() {
        setSize(550,500);
        setLocation(200, 200);
        setTitle("Escolha uma Foto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jFileChooser1 = new JFileChooser();
        jFileChooser1.setFileFilter(new FileTypeFilter(".jpg", "imagens"));
        jFileChooser1.setBackground(Color.WHITE);
        int showOpenDialog = jFileChooser1.showOpenDialog(this);
       
   }                 

   public String getPath(){
        String des="default.jpg";
       try{
            String x=jFileChooser1.getSelectedFile().getAbsolutePath();
           
            StringTokenizer str =new StringTokenizer(x,"\\");
            while (str.hasMoreTokens()) {
              des=str.nextToken();
            }
            File source =new File(x);
            File dest =new File("upload\\"+des);
        
            copyFileUsingStream(source,dest);
        } catch (IOException ex) {
            Logger.getLogger(Controle_EscolherFoto.class.getName()).log(Level.SEVERE, null, ex);
        }
       return des;
   }
    private static void copyFileUsingStream(File source, File dest) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    try {
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } finally {
        is.close();
        os.close();
    }
}
}
