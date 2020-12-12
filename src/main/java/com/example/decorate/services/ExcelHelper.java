package com.example.decorate.services;

import com.example.decorate.domain.ImageType;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.ExcelData;
import org.apache.poi.ss.usermodel.*;
import com.example.decorate.domain.dto.ImageModel;
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

import static com.example.decorate.domain.AttributeType.*;
import static com.example.decorate.domain.ImageType.SECONDARY_IMG;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

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
                            System.out.println(cellIdx);
                            currentCell.setCellType(STRING);
                            excelData.setName(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 1:
                            currentCell.setCellType(STRING);
                            excelData.setItemNumber(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 2:
                            currentCell.setCellType(NUMERIC);
                            excelData.setPrice((int) currentCell.getNumericCellValue());
                            System.out.println(currentCell.getNumericCellValue());
                            break;
                        case 3:
                            currentCell.setCellType(STRING);
                            excelData.setTypeOfProduct(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 4:
                            System.out.println(cellIdx);
                            for (String s : curtainTypeSlicer(currentCell.getStringCellValue())) {
                                excelData.getCurtainTypes().add(s);
                            }
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 5:
                            System.out.println(cellIdx);
                            currentCell.setCellType(STRING);
                            excelData.setProductFamily(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 6:
                            currentCell.setCellType(STRING);
                            excelData.setProductDesc(currentCell.getStringCellValue());
                            System.out.println(excelData.getProductDesc());
                            break;
                        case 7:
                            currentCell.setCellType(STRING);
                            excelData.setComposition(currentCell.getStringCellValue());
                            System.out.println(excelData.getComposition());
                            break;
                        case 8:
                            currentCell.setCellType(NUMERIC);
                            excelData.setHeight((int) currentCell.getNumericCellValue());
                            System.out.println(currentCell.getNumericCellValue());
                            break;
                        case 9:
                            currentCell.setCellType(NUMERIC);
                            excelData.setWidth((int) currentCell.getNumericCellValue());
                            System.out.println(currentCell.getNumericCellValue());
                            break;
                        case 10:
                            currentCell.setCellType(NUMERIC);
                            excelData.setPatternRep(currentCell.getNumericCellValue());
                            System.out.println(currentCell.getNumericCellValue());
                            break;
                        case 11:
                            currentCell.setCellType(STRING);
                            excelData.setTypeOfSize(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 12:
                            currentCell.setCellType(STRING);
                            excelData.setCleaningInst(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 13:
                            currentCell.setCellType(STRING);
                            excelData.setRecommendedGlue(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 14:
                            currentCell.setCellType(STRING);
                            excelData.setAnnotation(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 15:
                            currentCell.setCellType(NUMERIC);
                            excelData.setAbrasionResistance((int) currentCell.getNumericCellValue());

                            break;
                        case 16:
                            currentCell.setCellType(STRING);
                            excelData.getImageList().add(new ImageModel(ImageType.PRIMARY_IMG.toString(), currentCell.getStringCellValue()));

                            excelData.getImageList().add(new ImageModel(ImageType.PRIMARY_IMG.toString(), currentCell.getStringCellValue()));
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 17:
                            currentCell.setCellType(STRING);
                            for (ImageModel imageModel : imageSlicer(currentCell.getStringCellValue())) {
                                excelData.getImageList().add(imageModel);
                            }
                            System.out.println(currentCell.getStringCellValue());
                            if (currentCell.getStringCellValue() != null) {
                                for (ImageModel imageModel : imageSlicer(currentCell.getStringCellValue())) {
                                    excelData.getImageList().add(imageModel);
                                    System.out.println(imageModel);
                                }
                                System.out.println(currentCell.getStringCellValue());
                            }
                            break;
                        case 18:
                            currentCell.setCellType(STRING);
                            setAttributes(excelData, currentCell.getStringCellValue(), cellIdx);

                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 19:
                            currentCell.setCellType(STRING);
                            setAttributes(excelData, currentCell.getStringCellValue(), cellIdx);
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 20:
                            currentCell.setCellType(STRING);
                            setAttributes(excelData, currentCell.getStringCellValue(), cellIdx);
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

    public static List<ImageModel> imageSlicer(String images) {
        String[] urls = images.split(",");
        List<ImageModel> secImages = new ArrayList<>();
        for (String url : urls) {
            secImages.add(new ImageModel(SECONDARY_IMG.toString(), url));
        }
        return secImages;
    }

    public static List<String> curtainTypeSlicer(String types) {
        return Arrays.asList(types.split(","));
    }

    public static void setAttributes(ExcelData excelData, String stringCellValue, int cellIndex) {
        String[] productAttributes = stringCellValue.split(",");
        for (String attribute : productAttributes) {
            if (cellIndex == 18) {
                excelData.getAttributes().add(new AttributeCreationFormData(COLOR.toString(), attribute));
            }
            if (cellIndex == 19) {
                excelData.getAttributes().add(new AttributeCreationFormData(PATTERN.toString(), attribute));
            }
            if (cellIndex == 20) {
                excelData.getAttributes().add(new AttributeCreationFormData(STYLE.toString(), attribute));
            }
        }
    }
}
