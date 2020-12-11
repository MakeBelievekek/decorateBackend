package com.example.decorate.service;

import com.example.decorate.domain.dto.AttributeListItemData;
import com.example.decorate.domain.dto.ExcelData;
import com.example.decorate.domain.dto.ImageData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    //  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE = "application/octet-stream";
    static String[] HEADERs = {"Id", "Title", "Description", "Published"};
    static String SHEET = "Sheet1";

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
                            System.out.println(currentCell.getStringCellValue());
                            break;

                        case 1:
                            excelData.setComposition(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;

                        case 2:
                            excelData.setCurtainType(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;

                        case 3:
                            excelData.setHeight((int) currentCell.getNumericCellValue());
                            System.out.println((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            excelData.setWidth((int) currentCell.getNumericCellValue());
                            System.out.println((int) currentCell.getNumericCellValue());
                            break;
                        case 5:
                            excelData.setItemNumber(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 6:
                            excelData.setName(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 7:
                            excelData.setPatternRep((int) currentCell.getNumericCellValue());
                            System.out.println((int) currentCell.getNumericCellValue());
                            break;
                        case 8:
                            excelData.setPrice((int) currentCell.getNumericCellValue());
                            System.out.println((int) currentCell.getNumericCellValue());
                            break;
                        case 9:
                            excelData.setProductDesc(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 10:
                            excelData.setProductFamily(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 11:
                            excelData.setTypeOfSize(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 12:
                            excelData.setCleaningInst(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 13:
                            excelData.getImageList().add(new ImageData("PRIMARY_KEY", currentCell.getStringCellValue()));
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 14:
                            if (currentCell.getStringCellValue() != null) {
                                System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                                for (ImageData imageData : imageSlicer(currentCell.getStringCellValue())) {
                                    excelData.getImageList().add(imageData);
                                    System.out.println(imageData);
                                }
                                System.out.println(currentCell.getStringCellValue());
                            }
                            break;
                        case 15:
                            setAttributes(excelData, currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 16:
                            setAttributes(excelData, currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 17:
                            setAttributes(excelData, currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
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

    public static List<ImageData> imageSlicer(String images) {
        List<String> urls = Arrays.asList(images.split(","));
        List<ImageData> secImages = new ArrayList<>();
        for (String url : urls) {
            secImages.add(new ImageData("SECONDARY_KEY", url));
        }
        return secImages;
    }

    public static void setAttributes(ExcelData excelData, String stringCellValue) {
        List<String> attr = Arrays.asList(stringCellValue.split(","));
        for (String s : attr) {
            excelData.getAttributeListItemData().add(new AttributeListItemData());
        }
        List<AttributeListItemData> attributes = new ArrayList<>();
    }
}
