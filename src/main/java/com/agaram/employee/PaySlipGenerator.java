package com.agaram.employee;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class PaySlipGenerator {

	static String pattern = "MM-dd-yyyy";
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	private static final String FILE_PATH = "./AA_PaySlip_Generator.xls";


	private static List getEmployeeFromExcel() {
		List employeePayList = new ArrayList();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(FILE_PATH));
			HSSFWorkbook workbook = new HSSFWorkbook(fis);

			HSSFSheet sheet = workbook.getSheetAt(0);

			Iterator rowIterator = sheet.iterator();
			rowIterator.next();
			//iterating over each row
			while (rowIterator.hasNext()) {

				EmployeePay c = new EmployeePay();
				Row row = (Row) rowIterator.next();
				Iterator cellIterator = row.cellIterator();

				//Iterating over each cell (column wise)  in a particular row.
				while (cellIterator.hasNext()) {

					Cell cell = (Cell) cellIterator.next();

					//The Cell Containing String will is name.
					if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
						if (cell.getColumnIndex() == 1) {
							c.setMonth(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == 2) {
							c.setName(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == 3) {
							c.setEmploymentType(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == 4) {
							c.setLocation(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == 5) {
							c.setBankName(cell.getStringCellValue());
						}

						//The Cell Containing numeric value will contain marks
					} else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {

						if (cell.getColumnIndex() == 6) {
							c.setBankNumber(String.valueOf(cell.getNumericCellValue()));
						}
					} else if (Cell.CELL_TYPE_FORMULA == cell.getCellType()) {

						//Cell with index 2 contains marks in Science
						if (cell.getColumnIndex() == 9) {
							c.setBasic(String.valueOf(cell.getNumericCellValue()));
						} else if (cell.getColumnIndex() == 10) {
							c.setHRA(String.valueOf(cell.getNumericCellValue()));
						} else if (cell.getColumnIndex() == 11) {
							c.setConveyance(String.valueOf(cell.getNumericCellValue()));
						} else if (cell.getColumnIndex() == 12) {
							c.setMedical(String.valueOf(cell.getNumericCellValue()));
						} else if (cell.getColumnIndex() == 13) {
							c.setNetPay(String.valueOf(cell.getNumericCellValue()));
						}
					}

				}
				//end iterating a row, add all the elements of a row in list
				employeePayList.add(c);
			}

			fis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employeePayList;
	}

	/**
	 * Generates a PDF file with the text 'Hello World'
	 */
	public static void main(String[] args) {

		List<EmployeePay> empPayList = getEmployeeFromExcel();

		empPayList.forEach(empPay -> {
			// step 1: creation of a document-object
			Document document = new Document();
			try {
				// step 2:
				// we create a writer that listens to the document
				// and directs a PDF-stream to a file
				PdfWriter.getInstance(document,
					new FileOutputStream("./payslips/" + empPay.getName() + ".pdf"));

				// step 3: we open the document
				document.open();

				PdfPTable table = new PdfPTable(2);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

				Image jpg = Image.getInstance("./agaram.jpeg");
				jpg.setAlignment(0);
				table.addCell(jpg);

				Font f = new Font(Font.getFamilyIndex("Times-Roman"), 11.0f);
				Paragraph p = new Paragraph(
					"13b, Rangasamy Street, R.S. Puram, Coimbatore - 641002",
					f);
				p.setAlignment(Element.ALIGN_RIGHT);
				PdfPCell cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setUseBorderPadding(true);
				cell1.addElement(p);
				cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell1);
				document.add(table);

				Font f1 = new Font(Font.getFamilyIndex("Times-Roman"), 19.0f);
				f1.setStyle("bold");
				Paragraph p1 = new Paragraph("Payslip for " + empPay.getMonth() + " 2019", f1);
				p1.setAlignment(Element.ALIGN_CENTER);
				p1.setSpacingBefore(15.0f);
				document.add(p1);

				PdfPTable table1 = new PdfPTable(2);
				//table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

				PdfPCell cella = new PdfPCell();
				Paragraph pa = new Paragraph("Name", f);
				cella.addElement(pa);
				table1.addCell(pa);

				PdfPCell cellb = new PdfPCell();
				Paragraph pb = new Paragraph(empPay.getName(), f);
				cellb.addElement(pb);
				table1.addCell(pb);

				PdfPCell cellc = new PdfPCell();
				Paragraph pc = new Paragraph("Employment Type", f);
				cellc.addElement(pc);
				table1.addCell(pc);

				PdfPCell celld = new PdfPCell();
				Paragraph pd = new Paragraph(empPay.getEmploymentType(), f);
				celld.addElement(pd);
				table1.addCell(pd);

				PdfPCell celle = new PdfPCell();
				Paragraph pe = new Paragraph("Location", f);
				celle.addElement(pe);
				table1.addCell(pe);

				PdfPCell cellf = new PdfPCell();
				Paragraph pf = new Paragraph(empPay.getLocation(), f);
				cellf.addElement(pf);
				table1.addCell(pf);

				PdfPCell cellg = new PdfPCell();
				Paragraph pg = new Paragraph("Bank Account Name", f);
				cellg.addElement(pg);
				table1.addCell(pg);

				PdfPCell cellh = new PdfPCell();
				Paragraph ph = new Paragraph(empPay.getBankName(), f);
				cellh.addElement(ph);
				table1.addCell(ph);

				PdfPCell celli = new PdfPCell();
				Paragraph pi = new Paragraph("Bank Account Number", f);
				celli.addElement(pi);
				table1.addCell(pi);

				PdfPCell cellj = new PdfPCell();
				BigDecimal bd = new BigDecimal(empPay.getBankNumber().toString());
				long lonVal = bd.longValue();
				Paragraph pj = new Paragraph(String.valueOf(lonVal), f);
				cellj.addElement(pj);
				table1.addCell(pj);

				table1.setSpacingBefore(15.0f);
				document.add(table1);

				PdfPTable table2 = new PdfPTable(2);
				//table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

				PdfPCell cellk = new PdfPCell();
				Paragraph pk = new Paragraph("Description", f);
				cellk.addElement(pk);
				table2.addCell(pk);

				PdfPCell celll = new PdfPCell();
				Paragraph pl = new Paragraph("Earnings - INR", f);
				celll.addElement(pl);
				table2.addCell(pl);

				PdfPCell cellm = new PdfPCell();
				Paragraph pm = new Paragraph("Basic", f);
				cellm.addElement(pm);
				table2.addCell(pm);

				PdfPCell celln = new PdfPCell();
				Paragraph pn = new Paragraph(String.valueOf((int) Double.parseDouble(empPay.getBasic())), f);
				celln.addElement(pn);
				table2.addCell(pn);

				PdfPCell cello = new PdfPCell();
				Paragraph po = new Paragraph("House Rent Allowance", f);
				cello.addElement(po);
				table2.addCell(po);

				PdfPCell cellp = new PdfPCell();
				Paragraph pp = new Paragraph(String.valueOf((int) Double.parseDouble(empPay.getHRA())), f);
				cellp.addElement(pp);
				table2.addCell(pp);

				PdfPCell cellq = new PdfPCell();
				Paragraph pq = new Paragraph("Conveyance", f);
				cellq.addElement(pq);
				table2.addCell(pq);

				PdfPCell cellr = new PdfPCell();
				Paragraph pr = new Paragraph(String.valueOf((int) Double.parseDouble(empPay.getConveyance())), f);
				cellr.addElement(pr);
				table2.addCell(pr);

				PdfPCell cells = new PdfPCell();
				Paragraph ps = new Paragraph("Medical", f);
				cells.addElement(ps);
				table2.addCell(ps);

				PdfPCell cellt = new PdfPCell();
				Paragraph pt = new Paragraph(String.valueOf((int) Double.parseDouble(empPay.getMedical())), f);
				cellt.addElement(pt);
				table2.addCell(pt);

				PdfPCell cellu = new PdfPCell();
				Paragraph pu = new Paragraph("Net Payable Salary", f);
				cellu.addElement(pu);
				table2.addCell(pu);

				PdfPCell cellv = new PdfPCell();
				Paragraph pv = new Paragraph(String.valueOf((int) Double.parseDouble(empPay.getNetPay())), f);
				cellv.addElement(pv);
				table2.addCell(pv);

				table2.setSpacingBefore(15.0f);
				document.add(table2);

				PdfPTable tablez = new PdfPTable(1);
				tablez.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

				Paragraph px = new Paragraph("Amount in words: " + StringUtils.capitalize(
					EnglishNumberToWords.convert((int) Double.parseDouble(empPay.getNetPay())))
					+ "only", f);
				px.setAlignment(Element.ALIGN_LEFT);
				px.setSpacingBefore(15.0f);
				tablez.addCell(px);
				tablez.setSpacingBefore(3.0f);
				document.add(tablez);

				PdfPTable table9 = new PdfPTable(1);
				table9.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

				Paragraph pui = new Paragraph(
					"Payment Date: " + simpleDateFormat.format(new Date()),
					f);
				pui.setAlignment(Element.ALIGN_LEFT);
				pui.setSpacingBefore(15.0f);
				table9.addCell(pui);
				table9.setSpacingBefore(5.0f);
				document.add(table9);

			} catch (DocumentException de) {
				System.err.println(de.getMessage());
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}

			// step 5: we close the document
			document.close();
		});

	}
}
