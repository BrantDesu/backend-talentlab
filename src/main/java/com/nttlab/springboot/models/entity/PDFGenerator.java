package com.nttlab.springboot.models.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

    public static ResponseEntity<InputStreamResource> generatePDF(Sale sale)  {
	//public static ResponseEntity<InputStreamResource> generatePDF(Sale sale, ByteArrayOutputStream out)  {
    	List<CartItem> cartItems = sale.getCart().getCart_items();
    	double total = sale.getTotal();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(60);
            table.setWidths(new int[] { 1, 3, 1 });

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Paragraph("ID", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Paragraph("Producto", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Paragraph("Precio", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (CartItem item : cartItems) {

                PdfPCell cell;

                cell = new PdfPCell(new Paragraph(String.valueOf(item.getProduct().getIdProduct())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(item.getProduct().getName()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(String.valueOf(item.getProduct().getPrice())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfPTable totalTable = new PdfPTable(2);
            totalTable.setWidthPercentage(60);
            totalTable.setWidths(new int[] { 3, 1 });

            PdfPCell totalCell;
            totalCell = new PdfPCell(new Paragraph("Total", headFont));
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalTable.addCell(totalCell);

            totalCell = new PdfPCell(new Paragraph(String.valueOf(total)));
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalTable.addCell(totalCell);

            PdfWriter.getInstance(document, out);
            
            document.open();
            document.add(new Paragraph("Detalle de la orden numero: " + sale.getIdSale()));
            document.add(new Chunk());
            document.add(table);
            document.add(totalTable);
            document.close();
            
            
            
            // Crear un objeto InputStreamResource para enviar el archivo PDF como respuesta
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));

            // Configurar los encabezados de la respuesta HTTP para descargar el archivo PDF
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=boleta.pdf");

            //Crear la respuesta HTTP con el archivo PDF
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(out.size())
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
            //return out.toByteArray();
            

        } catch (DocumentException ex) {

            ex.printStackTrace();
        }

        return null;
        
    }
}
