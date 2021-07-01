package com.project.nando.climbmountain.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.pdfbox.util.NumberFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.nando.climbmountain.model.BookingDtl;
import com.project.nando.climbmountain.model.BookingHdr;
import com.project.nando.climbmountain.model.ClimberDisease;

@Service
public class ExportPdfService {

	@Autowired
	private BookingService bookingService;

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
	private DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public String writeToPDF(String path, BookingHdr booking) {

		String fileName = booking.getBookingNumber() + ".pdf";
		String file = path + fileName;
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			addMetaData(document, booking.getBookingNumber());
			addTitlePage(document);
			addSubtitlePage(document, booking);
			addClimberSection(document, booking);
			addPerlengkapanSection(document);
			addCatatanSection(document);
			addHargaSection(document, booking.getBookingDtls().size());
			addSignatureSection(document);
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return file;
	}

	private void addMetaData(Document document, String bookingNumber) {
		document.addTitle(bookingNumber);
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("System");
		document.addCreator("System");
	}

	private void addEmptyLine(Paragraph paragraph, int number, float leading) {
		for (int i = 0; i < number; i++) {
			Paragraph p = new Paragraph(" ");
			// default leading is 18
			p.setLeading(leading);
			paragraph.add(p);
		}
	}

	private void addTitlePage(Document document) throws DocumentException {
		Font titleFont = setFont(22, Font.BOLD);
		Chunk chunk = new Chunk("Jadwal Pendakian", titleFont);
		Paragraph preface = new Paragraph();
		preface.setAlignment(Paragraph.ALIGN_CENTER);
		preface.add(chunk);
		addEmptyLine(preface, 1, 12f);
		document.add(preface);
	}

	private void addSubtitlePage(Document document, BookingHdr booking) throws DocumentException {
		Font subTitleFont = setFont(12, 0);

		// prepare data for looping
		Map<Integer, Map<String, String>> data = new HashMap<Integer, Map<String, String>>();
		Map<String, String> insideData = new HashMap<String, String>();
		insideData.put("NO BOOKING", booking.getBookingNumber());
		data.put(1, insideData);

		insideData = new HashMap<String, String>();
		insideData.put("TANGGAL BERANGKAT", booking.getClimbingSchedule().getDate().format(dtf));
		data.put(2, insideData);

		insideData = new HashMap<String, String>();
		insideData.put("TANGGAL PULANG", booking.getDateReturn().format(dtf));
		data.put(3, insideData);

		Paragraph subtitle = new Paragraph();
		subtitle.setIndentationLeft(135);
		subtitle.setIndentationRight(135);

		float[] columnWidths = { 15, 1, 15 };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(55);
		data.entrySet().stream().forEach(p -> {
			p.getValue().entrySet().stream().forEach(q -> {
				PdfPCell cell1 = new PdfPCell();
				cell1.setBorder(0);
				cell1.setPhrase(new Phrase(q.getKey(), subTitleFont));
				table.addCell(cell1);

				PdfPCell cell2 = new PdfPCell();
				cell2.setBorder(0);
				cell2.setPhrase(new Phrase(":", subTitleFont));
				table.addCell(cell2);

				PdfPCell cell3 = new PdfPCell();
				cell3.setBorder(0);
				cell3.setPhrase(new Phrase(q.getValue(), subTitleFont));
				table.addCell(cell3);
			});

		});

		document.add(table);

		subTitleFont.setStyle(Font.BOLDITALIC);
		Paragraph rekomendasi = new Paragraph();
		rekomendasi.setAlignment(Paragraph.ALIGN_CENTER);
		rekomendasi.add(
				new Phrase("(Rekomendasi: " + (booking.isNeedTourGuide() ? "Butuh" : "Tidak Butuh") + " Tour Guide)",
						subTitleFont));
		document.add(rekomendasi);

	}

	private void addHeaderClimberSection(PdfPTable table, Font font) {

		String[] headers = { "No", "Nama", "L/P", "Tgl Lahir", "Usia", "Alamat", "No Identitas" };

		Font headerFont = setFont(10, 1);
		PdfPCell cellSpan = new PdfPCell(new Phrase("PESERTA: ", headerFont));
		cellSpan.setBorder(0);
		cellSpan.setColspan(headers.length);
		table.addCell(cellSpan);

		Stream.of(headers).forEach(p -> {
			PdfPCell header = new PdfPCell();
			header.setPhrase(new Phrase(p, font));
			table.addCell(header);
		});

	}

	private void addClimberSection(Document document, BookingHdr booking) throws DocumentException {
		Font climberSectionFont = setFont(10, 0);
		Paragraph emptyPr = new Paragraph();
		addEmptyLine(emptyPr, 1, 12f);
		document.add(emptyPr);

		float[] columnWidths = { 2, 12, 2.5f, 6, 3, 20, 12 };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100);
		addHeaderClimberSection(table, climberSectionFont);

		List<BookingDtl> dtls = booking.getBookingDtls();
		dtls.sort(Comparator.comparingInt(BookingDtl::getId));

		int i = 0;
		for (BookingDtl dtl : dtls) {
			++i;
			table.addCell(new Phrase(String.valueOf(i), climberSectionFont));
			table.addCell(new Phrase(dtl.getClimber().getName(), climberSectionFont));
			table.addCell(new Phrase(dtl.getClimber().getGender(), climberSectionFont));
			table.addCell(new Phrase(dtl.getClimber().getDob().format(dtf2), climberSectionFont));
			table.addCell(new Phrase(String.valueOf(dtl.getClimber().getAge()), climberSectionFont));
			table.addCell(new Phrase(dtl.getClimber().getAddress(), climberSectionFont));
			table.addCell(new Phrase(dtl.getClimber().getIdentityNumber(), climberSectionFont));

		}

		document.add(table);

		addClimberSectionCont(document, booking);

	}

	private void addHeaderClimberSectionCont(PdfPTable table, Font font) {

		String[] headers = { "No Telp", "No Telp Kel", "Punya ltr blkg penyakit (Y/T)", "Nama Penyakit",
				"Pernah Melakukan Pendakian (Y/T)", "Jumlah Pendakian" };

		Stream.of(headers).forEach(p -> {
			PdfPCell header = new PdfPCell();
			header.setPhrase(new Phrase(p, font));
			table.addCell(header);
		});

	}

	private void addClimberSectionCont(Document document, BookingHdr booking) throws DocumentException {
		Font climberDiseaseSectionFont = setFont(10, 0);
		Paragraph emptyPr = new Paragraph();
		addEmptyLine(emptyPr, 1, 12f);
		document.add(emptyPr);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		addHeaderClimberSectionCont(table, climberDiseaseSectionFont);

		List<BookingDtl> dtls = booking.getBookingDtls();
		dtls.sort(Comparator.comparingInt(BookingDtl::getId));

		for (BookingDtl dtl : booking.getBookingDtls()) {
			table.addCell(new Phrase(dtl.getClimber().getPhoneNumber(), climberDiseaseSectionFont));
			table.addCell(new Phrase(dtl.getClimber().getFamilyPhoneNumber(), climberDiseaseSectionFont));
			String hasDisease = dtl.getClimber().getIsHasDisease() ? "Y" : "T";
			table.addCell(new Phrase(hasDisease, climberDiseaseSectionFont));

			String diseases = (dtl.getClimber().getDiseases() == null ? "-" : dtl.getClimber().getDiseases());
			table.addCell(new Phrase(diseases, climberDiseaseSectionFont));

			String hasEverClimb = (dtl.getClimber().isHasEverClimb() ? "Y" : "T");
			table.addCell(new Phrase(hasEverClimb, climberDiseaseSectionFont));

			String numberOfClimbs = (dtl.getClimber().getNumberOfClimbs() == 0 ? "-"
					: String.valueOf(dtl.getClimber().getNumberOfClimbs()));
			table.addCell(new Phrase(numberOfClimbs, climberDiseaseSectionFont));

		}

		document.add(table);

	}

	private void addHeaderPelengkapanSection(PdfPTable table, Font font) {
		String[] headers = { "Perlengkapan", "Jumlah", "Perbekalan", "Jumlah", "Obat-Obatan", "Jumlah" };

		Font headerFont = setFont(10, 1);
		PdfPCell cellSpan = new PdfPCell(new Phrase("PERLENGKAPAN: ", headerFont));
		cellSpan.setBorder(0);
		cellSpan.setColspan(headers.length);
		table.addCell(cellSpan);

		Stream.of(headers).forEach(p -> {
			PdfPCell header = new PdfPCell();
			header.setPhrase(new Phrase(p, font));
			table.addCell(header);
		});

	}

	private void addPerlengkapanSection(Document document) throws DocumentException {
		Font perlengkapanSectionFont = setFont(10, 0);
		Paragraph emptyPr = new Paragraph();
		addEmptyLine(emptyPr, 1, 12f);
		document.add(emptyPr);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		addHeaderPelengkapanSection(table, perlengkapanSectionFont);

		String[] perlengkapans = { "Tenda", "Kompor", "Nesting", "Gas/Bahan Bakar", "Korek Api", "Flysheet",
				"Sleeping Bag", "Senter/Headlamp", "Baterai Cadangan", "Ransel", "Matras", "Jas Hujan", "Baju Ganti",
				"Jaket", "Topi", "Kaos Tangan", "Kaos Kaki", "Masker/Buff", "Sepatu Tracking", "Sendal Tracking",
				"Jam Tangan", "Handphone", "Tracking Pool", "Piring", "Gelas", "Sendok", "Pisau Lipat" };

		Stream.of(perlengkapans).forEach(p -> {
			PdfPCell header = new PdfPCell();
			header.setPhrase(new Phrase(p, perlengkapanSectionFont));
			table.addCell(header);
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
		});

		document.add(table);
	}

	private void addHargaSection(Document document, int totalClimbers) throws DocumentException {
		Font hargaSectionFont = setFont(10, 0);
		Paragraph emptyPr = new Paragraph();
		addEmptyLine(emptyPr, 1, 12f);
		document.add(emptyPr);

		float[] columnWidths = { 16, 1, 10 };

		PdfPTable table = new PdfPTable(columnWidths);
		table.setHorizontalAlignment(table.ALIGN_LEFT);
		table.setWidthPercentage(40);

		Font headerFont = setFont(10, 1);
		PdfPCell cellSpan = new PdfPCell(new Phrase("INFO HARGA: ", headerFont));
		cellSpan.setBorder(0);
		cellSpan.setColspan(3);
		table.addCell(cellSpan);

		// prepare data for looping
		Map<Integer, Map<String, String>> data = new HashMap<Integer, Map<String, String>>();
		Map<String, String> insideData = new HashMap<String, String>();
		insideData.put("Masuk per Orang", "Rp. 15.000,-");
		data.put(1, insideData);

		insideData = new HashMap<String, String>();
		insideData.put("Kegiatan per Orang", "Rp. 7.000,-");
		data.put(2, insideData);

		insideData = new HashMap<String, String>();
		insideData.put("Asuransi per Orang", "Rp. 3.000,-");
		data.put(3, insideData);

		insideData = new HashMap<String, String>();
		insideData.put("Total", "Rp. 25.000,-");
		data.put(4, insideData);

		insideData = new HashMap<String, String>();
		long price = totalClimbers * 25000;
		insideData.put("Jumlah Orang (" + totalClimbers + ")" + " x Rp. 25.000,-",
				"Rp. " + formatCurrency(price) + ",-");
		data.put(5, insideData);

		data.entrySet().stream().forEach(p -> {

			p.getValue().entrySet().stream().forEach(q -> {
				PdfPCell cell1 = new PdfPCell();
				cell1.setBorder(0);
				cell1.setPhrase(new Phrase(q.getKey(), hargaSectionFont));
				table.addCell(cell1);

				PdfPCell cell2 = new PdfPCell();
				cell2.setBorder(0);
				cell2.setPhrase(new Phrase(":", hargaSectionFont));
				table.addCell(cell2);

				PdfPCell cell3 = new PdfPCell();
				cell3.setBorder(0);
				cell3.setPhrase(new Phrase(q.getValue(), hargaSectionFont));
				table.addCell(cell3);
			});

		});

		document.add(table);

	}

	private void addCatatanSection(Document document) throws DocumentException {
		Font catatanFontBold = setFont(10, 1);
		Paragraph catatan = new Paragraph();
		catatan.add(new Phrase("CATATAN: ", catatanFontBold));

		Font catatanFontItalic = setFont(8, 0);
		catatanFontItalic.setStyle(Font.ITALIC);

		addEmptyLine(catatan, 1, 1f);
		catatan.add(new Phrase(
				"* FORM INI DI ISI OLEH KETUA ROMBONGAN YANG KEMUDIAN DIPERIKSA OLEH PETUGAS DAN DITANDATANGANI OLEH PETUGAS DAN PENDAKI",
				catatanFontItalic));

		addEmptyLine(catatan, 1, 1f);
		catatan.add(new Phrase(
				"** JIKA SAMPAH BAWAAN TIDAK DIBAWA TURUN, YANG BERSANGUTAN AKAN DIKENAKAN SANKSI OLEH PETUGAS",
				catatanFontItalic));

		addEmptyLine(catatan, 1, 1f);
		catatan.add(new Phrase(
				"*** YANG BERSANGKUTAN MENDATANGANI LEMBAR ISI SEBAGAI PERNYATAAN KESANGGUPAN TERHADAP KESEPAKATAN YANG DI BUAT",
				catatanFontItalic));

		document.add(catatan);
	}

	private void addSignatureSection(Document document) throws DocumentException {
		Font signatureFontBold12 = setFont(12, 1);
		Font signatureFontBold10 = setFont(10, 1);

		Paragraph emptyPr = new Paragraph();
		addEmptyLine(emptyPr, 1, 12f);
		document.add(emptyPr);

		Paragraph signature = new Paragraph();
		signature.setAlignment(Paragraph.ALIGN_CENTER);
		signature.add(new Phrase("SAYA SIAP MEMBAWA TURUN SAMPAH KEMBALI", signatureFontBold12));
		addEmptyLine(signature, 1, 12f);
		document.add(signature);

		String[] listSignatures = { "PETUGAS CEK NAIK", "PETUGAS CEK TURUN", "KETUA ROMBONGAN" };
		String[] listSignatures2 = { "(                                      )",
				"(                                         )", "(                                        )" };

		PdfPTable table = new PdfPTable(3);

		Stream.of(listSignatures).forEach(p -> {
			PdfPCell cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPhrase(new Phrase(p, signatureFontBold10));
			table.addCell(cell);
		});

		for (int i = 0; i < 9; i++) {
			PdfPCell cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPhrase(new Phrase(" "));
			table.addCell(cell);
		}

		Stream.of(listSignatures2).forEach(p -> {
			PdfPCell cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPhrase(new Phrase(p, signatureFontBold10));
			table.addCell(cell);
		});

		document.add(table);
	}

	private Font setFont(int fontSize, int boldSize) {
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, fontSize, boldSize);
		return font;
	}

	private String formatCurrency(long value) {
		DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
		symbols.setGroupingSeparator('.');
		DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
		return formatter.format(value);
	}

}
