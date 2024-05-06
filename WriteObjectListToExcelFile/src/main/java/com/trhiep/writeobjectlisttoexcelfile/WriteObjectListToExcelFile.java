/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.trhiep.writeobjectlisttoexcelfile;

import com.trhiep.writeobjectlisttoexcelfile.models.Customer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author tranh
 */
public class WriteObjectListToExcelFile {

    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().id(1L).fullName("Nguyen Van A").phone("0863575672").note("Priority 1").build());
        customers.add(Customer.builder().id(2L).fullName("Tran Van B").phone("0863565362").note("Priority 2").build());
        customers.add(Customer.builder().id(3L).fullName("Pham Van C").phone("0863574367").note("Priority 1").build());
        customers.add(Customer.builder().id(4L).fullName("Nguyen Thi D").phone("0863574672").build());
        customers.add(Customer.builder().id(5L).fullName("Nguyen Tran E").phone("0863574545").build());
        customers.add(Customer.builder().id(6L).fullName("Nguyen Van F").phone("0863572323").note("Priority 3").build());
        customers.add(Customer.builder().id(7L).fullName("Tran G").phone("0863578745").note("Priority 1").build());
        customers.add(Customer.builder().id(8L).fullName("Bui Van H").phone("08665475235").build());
        customers.add(Customer.builder().id(9L).fullName("Nguyen Quang I").phone("0863554564").note("Priority 1").build());
        customers.add(Customer.builder().id(10L).fullName("Nguyen Ba K").phone("08635235657").build());

        writeObjectsToExcel(customers, "customers.xlsx");
    }

    public static void writeObjectsToExcel(List<Customer> customers, String filePath) {
        // Tạo một workbook mới
        try (Workbook workbook = new XSSFWorkbook()) {
            // Tạo một trang trong workbook
            Sheet sheet = workbook.createSheet("Customers");

            // Tạo hàng dữ liệu đầu tiên là tiêu đề
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Fullname", "Phone", "Note"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Ghi dữ liệu của mỗi đối tượng vào các hàng
            int rowNum = 1;
            for (Customer customer : customers) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(customer.getId());
                row.createCell(1).setCellValue(customer.getFullName());
                row.createCell(2).setCellValue(customer.getPhone());
                row.createCell(3).setCellValue(customer.getNote());
                System.out.println("Row content: " + row.toString());
            }

            // Kiểm tra file đã tồn tại hay chưa
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }

            // Ghi workbook vào tệp
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
            System.out.println("Excel file written successfully.");
            workbook.close();
        } catch (IOException e) {
            System.out.println("Loi");
        }
    }
}
