/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.util.Calendar;

public class HeaderFooterPageEvent extends PdfPageEventHelper{
    public void onStartPage(PdfWriter writer, Document document){
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER,new Phrase("GAVI"), 300, 800, 0);
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER,new Phrase("Gestao de Aluguer e Venda de Imoveis"), 300, 750, 0);
    }
    public void onEndPage(PdfWriter writer, Document document){
        Calendar cal = Calendar.getInstance();
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER,new Phrase("Horario :"+cal.getTime()), 110, 30, 0);
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER,new Phrase("Pagina "+ (document.getPageNumber()+1 )), 550, 30, 0);
    }
}
