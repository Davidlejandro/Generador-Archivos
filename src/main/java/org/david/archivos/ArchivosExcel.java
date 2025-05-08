package org.david.archivos;

// Importaciones necesarias para trabajar con Servlets y Apache POI
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // Clase para crear archivos .xlsx
import org.apache.poi.ss.usermodel.*;              // Interfaces comunes de POI (Workbook, Sheet, Row, Cell)

import java.io.IOException;

@WebServlet("/manejoExcel/url-get") // Define la URL que activará este servlet
public class ArchivosExcel extends HttpServlet {

    // Método que responde a solicitudes HTTP GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Crear un libro de trabajo Excel en formato .xlsx
        Workbook workbook = new XSSFWorkbook();

        // Crear una hoja dentro del libro llamada "Datos"
        Sheet sheet = workbook.createSheet("Datos");

        // Crear la primera fila (fila 0) que será la cabecera
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nombre"); // Celda A1: "Nombre"
        header.createCell(1).setCellValue("Edad");   // Celda B1: "Edad"

        // Crear una segunda fila (fila 1) con datos
        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue("Juan");      // Celda A2: "Juan"
        row.createCell(1).setCellValue(28);          // Celda B2: 28

        // Configurar el tipo de contenido como Excel moderno (.xlsx)
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Forzar que el navegador lo descargue como "archivo.xlsx"
        resp.setHeader("Content-Disposition", "attachment; filename=archivo.xlsx");

        // Escribir el contenido del Excel directamente en la respuesta HTTP
        workbook.write(resp.getOutputStream());

        // Cerrar el libro de trabajo para liberar memoria
        workbook.close();
    }
}