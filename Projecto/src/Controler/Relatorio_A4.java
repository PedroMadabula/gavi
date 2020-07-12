package Controler;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.PageSize;
import Model.Dao.DAO_Anuncio;
import Model.Dao.DAO_Relatorio;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.Graphics2D;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import Model.Dao.DAO_Usuario;
import Model.Value.Anuncios;
import Model.Value.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Relatorio_A4 {
    private String ficheiroNomes;
    private Object dauU;
    public Relatorio_A4(){
        try {
            ficheiroNomes = "Relatorio_Estatisticas.pdf";
            gerarPdf_A4();
        } catch (Exception ex) {
            Logger.getLogger(Relatorio_A4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gerarPdf_A4()throws Exception{
        
       // PageSize.A4.setBackgroundColor(new BaseColor(0xDF , 0xFF , 0xDE));
        
        Document d = new Document(PageSize.A4);
        //PdfWriter.getInstance(d, new FileOutputStream(ficheiroNomes));
        PdfWriter  writer  = PdfWriter.getInstance(d, new FileOutputStream(ficheiroNomes));
        HeaderFooterPageEvent header = new HeaderFooterPageEvent();
        d.open();
        header.onEndPage(writer, d);
        header.onStartPage(writer, d);
        
        
        d.add(new Paragraph(""));


        DAO_Relatorio daoR= new DAO_Relatorio();
        java.util.List<String> listaAds = daoR.listarAdUsuario(Secure.Bi);
        
        d.add(new Paragraph("   "));
        d.add(new Paragraph("   "));
        d.add(new Paragraph("   "));d.add(new Paragraph("   "));
        
        d.add(new Paragraph("Lista de Anuncios de um Usuarios"));
        d.add(new Paragraph("   "));
        PdfPTable tAds = new PdfPTable(7);
        tAds.setWidthPercentage(100);
        
        tAds.addCell(" Id Imovel");tAds.addCell("Preco");tAds.addCell("Bairro");tAds.addCell("TipoImovel");tAds.addCell("Bi");
        tAds.addCell("Nome");tAds.addCell("Apelido");
       
        for (int i = 0; i < listaAds.size(); i++) {
                tAds.addCell(listaAds.get(i)+"");
            }
        d.add(tAds);

        d.close();
        JOptionPane.showMessageDialog(null,"Relatorio Criado");
    }
}
