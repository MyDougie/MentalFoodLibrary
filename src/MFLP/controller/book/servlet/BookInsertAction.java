package MFLP.controller.book.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import MFLP.controller.Action;
import MFLP.model.dto.BookDTO;
import MFLP.model.service.MflpBookService;

public class BookInsertAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. multipartRequest ����

		try {

			int num = MflpBookService.bookNum();
			String root = request.getServletContext().getRealPath("/");
			String pathname = root + "save/";
			File f = new File(pathname);
			if (!f.exists()) {
				// ������ �������� ������ ���� ����
				f.mkdirs();
			}

			int maxSize = 5 * 1024 * 1024;
			String encoding = "UTF-8";

			MultipartRequest m = new MultipartRequest(request, pathname, maxSize, encoding,
					new DefaultFileRenamePolicy());
			// 2. �Ѿ���� �� �ޱ�

			String title = m.getParameter("title");
			String description = m.getParameter("description");
			String writer = m.getParameter("writer");
			String writeDate = m.getParameter("writeDate");
			String publisher = m.getParameter("publisher");
			String categoryNo = m.getParameter("category");
			String bookImg = m.getFilesystemName("file");

			int yy = new Date(writeDate).getYear();
			int mm = new Date(writeDate).getMonth();
			int dd = new Date(writeDate).getDate();
			String date = (yy + 1900) + "/" + (mm + 1) + "/" + dd;

			/*
			 * 3. ��ȿ�� �˻� (�Է�����üũ) ���� �ϳ��� �����ϸ� �������ܹ߻� (throw new
			 * Exception("���� ������� ����")) �̶� catch���� ȣ��ǰ� catch ������ ���ܸ޼��� ���� �̵�
			 */

			// 4. ���ܰ� ������ Electronics �����ؼ� ���� �����Ѵ�.
			BookDTO book = new BookDTO();
			book.setTitle(title);
			book.setDescription(description);
			book.setWriter(writer);
			book.setWriteDate(date);
			book.setPublisher(publisher);
			book.setCategoryNo(Integer.parseInt(categoryNo));

			// 5. ����, ������ ÷�εǾ��ٸ� �����̸��� ���ϻ����� ����
			if (bookImg != null) {
				book.setBookImg(bookImg);
				File file = m.getFile("file");
			}

			// 6. insert ȣ���ϰ� �� ����� �����̸�
			// �ٽ� elec?command=list�� �̵��Ѵ� (response.sendRedirect)
			// ElectronicsService.insert(electronics);

			int result = MflpBookService.bookInsert(book);
			if (result > 0) {
				request.setAttribute("tabState", 2);
				request.setAttribute("successMsg", "������ϿϷ�");
				response.sendRedirect("main.jsp?contentPage=/admin/adminPage.jsp");
				return;
			} else {
				request.setAttribute("tabState", 2);
				request.setAttribute("errorMsg", "�����߽��ϴ�.");
				response.sendRedirect("main.jsp?contentPage=/admin/adminPage.jsp");
			}

		} catch (Exception e) {
			request.setAttribute("tabState", 2);
			request.setAttribute("errorMsg", "�����߽��ϴ�.");
			response.sendRedirect("main.jsp?contentPage=/admin/adminPage.jsp");

		}
	}

}
