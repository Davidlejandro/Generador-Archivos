package org.david.archivos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/manejoPDF/url-get")
public class ArchivosPDF extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=Bienvenido.PDF");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, resp.getOutputStream());
            document.open();
            document.add(new Paragraph("Este es un PDF generado desde el servidor."));
            document.add(new Paragraph("Nombre: Juan"));
            document.add(new Paragraph("Edad: 28"));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
