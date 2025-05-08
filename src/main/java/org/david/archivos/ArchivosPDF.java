package org.david.archivos;

// Librerías necesarias para Servlets y PDF (iText)
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

@WebServlet("/manejoPDF/url-get") // Mapea el servlet a esta URL
public class ArchivosPDF extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Indica que el contenido devuelto será un archivo PDF
        resp.setContentType("application/pdf");

        // Hace que el navegador descargue el archivo como "Bienvenido.PDF"
        resp.setHeader("Content-Disposition", "attachment; filename=Bienvenido.PDF");

        try {
            // Crear un nuevo documento PDF
            Document document = new Document();

            // Asocia la salida del PDF con la respuesta HTTP
            PdfWriter.getInstance(document, resp.getOutputStream());

            // Abre el documento para empezar a escribir
            document.open();

            // Añade contenido al PDF (texto simple)
            document.add(new Paragraph("Este es un PDF generado desde el servidor."));
            document.add(new Paragraph("Nombre: Juan"));
            document.add(new Paragraph("Edad: 28"));

            // Cierra el documento y finaliza la escritura
            document.close();

        } catch (Exception e) {
            // Muestra cualquier error en consola
            e.printStackTrace();
        }
    }
}
