package com.example.decorate.service;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.dto.ExcelData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
  //  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE = "application/octet-stream";
    static String[] HEADERs = {"Id", "Title", "Description", "Published"};
    static String SHEET = "Tutorials";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<ExcelData> excelToTutorials(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<ExcelData> excelDataList = new ArrayList<ExcelData>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                ExcelData excelData = new ExcelData();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            excelData.setTypeOfProduct(currentCell.getStringCellValue());
                            break;

                        case 1:
                            excelData.setComposition(currentCell.getStringCellValue());
                            break;

                        case 2:
                            excelData.setCurtainType(currentCell.getStringCellValue());
                            break;

                        case 3:
                            excelData.setHeight((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            excelData.setWidth((int) currentCell.getNumericCellValue());
                            break;
                        case 5:
                            excelData.setItemNumber(currentCell.getStringCellValue());
                            break;
                        case 6:
                            excelData.setName(currentCell.getStringCellValue());
                            break;
                        case 7:
                            excelData.setPatternRep((int) currentCell.getNumericCellValue());
                            break;
                        case 8:
                            excelData.setPrice((int) currentCell.getNumericCellValue());
                            break;
                        case 9:
                            excelData.setProductDesc(currentCell.getStringCellValue());
                            break;
                        case 10:
                            excelData.setProductFamily(currentCell.getStringCellValue());
                            break;
                        case 11:
                            excelData.setTypeOfSize(currentCell.getStringCellValue());
                            break;
                        case 12:
                            excelData.setCleaningInst(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                excelDataList.add(excelData);
            }
            workbook.close();

            return excelDataList;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Curtain> convert(List<ExcelData> products) {
        return null;
    }
}
