package com.qed42.qa.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelManager {

	public String filepath1;

	/**
	 * Performs "SELECT * From <sheetName>". Returns all data from an excel sheet.
	 * 
	 * @param filepath  - Path of the excel file directory
	 * @param excelName - Excel file name
	 * @param sheetName - Sheet name in an excel file
	 * @return
	 * @throws FilloException
	 */
	public List<HashMap<String, String>> getAllData(String filepath, String excelName, String sheetName)
			throws FilloException {
		Fillo fillo = new Fillo();
		filepath1 = filepath + excelName;
		List<HashMap<String, String>> recordList = new ArrayList<>();

		Connection connection = fillo.getConnection(filepath1);
		String strQuery = "Select * from" + " " + sheetName;
		Recordset recordset = connection.executeQuery(strQuery);
		int columnCount = recordset.getFieldNames().size();

		while (recordset.next()) {
			HashMap<String, String> rowData = new HashMap<String, String>();

			for (int i = 0; i <= columnCount - 1; i++) {
				String fieldKey = recordset.getFieldNames().get(i);
				String fieldValue = recordset.getField(fieldKey);
				rowData.put(fieldKey, fieldValue);
			}
			recordList.add(rowData);
		}
		recordset.close();
		connection.close();
		return recordList;
	}

	/**
	 * Performs "SELECT * From <sheetName> Where <whereClause>". Returns data that
	 * meet the condition(s) given in where clause, from an excel sheet.
	 * 
	 * @param filepath    - Path of the excel file directory
	 * @param excelName   - Excel file name
	 * @param sheetName   - Sheet name in an excel file
	 * @param whereClause - Mention single or multiple conditions. With or without
	 *                    LIKE Operator ex. "Select * from Sheet1 where userName
	 *                    like 'Cod%'"
	 * @return
	 * @throws FilloException
	 */
	public List<HashMap<String, String>> getDataWithWhere(String filepath, String excelName, String sheetName,
			String whereClause) throws FilloException {
		Fillo fillo = new Fillo();
		filepath1 = filepath + excelName;
		List<HashMap<String, String>> recordList = new ArrayList<>();

		Connection connection = fillo.getConnection(filepath1);
		String strQuery = "Select * from" + " " + sheetName + " " + whereClause;
		Recordset recordset = connection.executeQuery(strQuery);
		int columnCount = recordset.getFieldNames().size();

		while (recordset.next()) {
			HashMap<String, String> rowData = new HashMap<String, String>();

			for (int i = 0; i <= columnCount - 1; i++) {
				String fieldKey = recordset.getFieldNames().get(i);
				String fieldValue = recordset.getField(fieldKey);
				rowData.put(fieldKey, fieldValue);
			}
			recordList.add(rowData);
		}
		recordset.close();
		connection.close();
		return recordList;
	}

	/**
	 * Performs "SELECT * From <sheetName> Where <fieldName> <operator> <fieldValue>
	 * ". Returns data that meet the condition given in where clause, from an excel
	 * sheet.
	 * 
	 * @param filepath   - Path of the excel file directory
	 * @param excelName  - Excel file name
	 * @param sheetName  - Sheet name in an excel file
	 * @param fieldName  - Column name
	 * @param fieldValue - Column value
	 * @param operator   - ex. '=','!=','<','>','<=','>='
	 * @return
	 * @throws FilloException
	 */
	public List<HashMap<String, String>> getDataWithWhere(String filepath, String excelName, String sheetName,
			String fieldName, String operator, String fieldValue) throws FilloException {
		Fillo fillo = new Fillo();
		filepath1 = filepath + excelName;
		List<HashMap<String, String>> recordList = new ArrayList<>();

		Connection connection = fillo.getConnection(filepath1);
		String strQuery = "Select * from" + " " + sheetName + " " + "where " + fieldName + operator + "'" + fieldValue
				+ "'";
		Recordset recordset = connection.executeQuery(strQuery);
		int columnCount = recordset.getFieldNames().size();

		while (recordset.next()) {
			HashMap<String, String> rowData = new HashMap<String, String>();

			for (int i = 0; i <= columnCount - 1; i++) {
				String fieldKey = recordset.getFieldNames().get(i);
				String fieldValue1 = recordset.getField(fieldKey);
				rowData.put(fieldKey, fieldValue1);
			}
			recordList.add(rowData);
		}
		recordset.close();
		connection.close();
		return recordList;
	}

	/**
	 * Performs "INSERT into" <sheetName> ( <fieldNames>) Values (<fieldValue>) ".
	 * Inserts record into an excel sheet.
	 * 
	 * @param filepath    - Path of the excel file directory
	 * @param excelName   - Excel file name
	 * @param sheetName   - Sheet name in an excel file
	 * @param fieldNames  - Column names Ex. "Username,Password"
	 * @param fieldValues - Column values Ex. "'abc','123'"
	 * @return
	 * @throws FilloException
	 */
	public void insertRowData(String filepath, String excelName, String sheetName, String fieldNames,
			String fieldValues) throws FilloException {

		Fillo fillo = new Fillo();
		filepath1 = filepath + excelName;

		Connection connection = fillo.getConnection(filepath1);
		String strQuery = "INSERT into " + sheetName + " (" + fieldNames + ") Values(" + fieldValues + ")";
		connection.executeUpdate(strQuery);
		connection.close();
	}

	/**
	 * Performs "UPDATE" <sheetName> set <fieldName> = <fieldValue> where
	 * <whereClause>) ". Updates a field for records matching the condition given in
	 * where clause.
	 * 
	 * @param filepath    - Path of the excel file directory
	 * @param excelName   - Excel file name
	 * @param sheetName   - Sheet name in an excel file
	 * @param whereClause
	 * @param fieldName   - Column name Ex. "Username"
	 * @param fieldValue  - Column value Ex. "'abc'"
	 * @return
	 * @throws FilloException
	 */
	public void updateDataWithWhere(String filepath, String excelName, String sheetName, String fieldName,
			String fieldValue, String whereClause) throws FilloException {
		Fillo fillo = new Fillo();
		filepath1 = filepath + excelName;
		Connection connection = fillo.getConnection(filepath);

		String strQuery = "Update " + sheetName + " set " + fieldName + "=" + fieldValue + " " + whereClause;
		connection.executeUpdate(strQuery);
		connection.close();
	}

	/**
	 * Performs "UPDATE" <sheetName> set <fieldName> = <fieldValue> where
	 * <whereClause>) ". Updates field of all records.
	 * 
	 * @param filepath   - Path of the excel file directory
	 * @param excelName  - Excel file name
	 * @param sheetName  - Sheet name in an excel file
	 * @param fieldName  - Column name Ex. "Username"
	 * @param fieldValue - Column value Ex. "'abc'"
	 * @return
	 * @throws FilloException
	 */
	public void updateData(String filepath, String excelName, String sheetName, String fieldName, String fieldValue)
			throws FilloException {

		Fillo fillo = new Fillo();
		filepath1 = filepath + excelName;

		Connection connection = fillo.getConnection(filepath);
		String strQuery = "Update " + sheetName + " set " + fieldName + "=" + fieldValue;
		connection.executeUpdate(strQuery);
		connection.close();
	}

}
