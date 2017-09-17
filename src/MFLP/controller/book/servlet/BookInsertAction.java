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

		// 1. multipartRequest 생성

		try {

			int num = MflpBookService.bookNum();
			String root = request.getServletContext().getRealPath("/");
			String pathname = root + "save/";
			File f = new File(pathname);
			if (!f.exists()) {
				// 폴더가 존재하지 않으면 폴더 생성
				f.mkdirs();
			}

			int maxSize = 5 * 1024 * 1024;
			String encoding = "UTF-8";

			MultipartRequest m = new MultipartRequest(request, pathname, maxSize, encoding,
					new DefaultFileRenamePolicy());
			// 2. 넘어오는 값 받기

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
			 * 3. 유효성 검사 (입력유무체크) 값이 하나라도 부족하면 강제예외발생 (throw new
			 * Exception("값이 충분하지 않음")) 이때 catch블럭이 호출되고 catch 블럭에서 예외메세지 저장 이동
			 */

			// 4. 예외가 없으면 Electronics 생성해서 정보 저장한다.
			BookDTO book = new BookDTO();
			book.setTitle(title);
			book.setDescription(description);
			book.setWriter(writer);
			book.setWriteDate(date);
			book.setPublisher(publisher);
			book.setCategoryNo(Integer.parseInt(categoryNo));

			// 5. 만약, 파일이 첨부되었다면 파일이름과 파일사이즈 저장
			if (bookImg != null) {
				book.setBookImg(bookImg);
				File file = m.getFile("file");
			}

			// 6. insert 호출하고 그 결과가 성공이면
			// 다시 elec?command=list로 이동한다 (response.sendRedirect)
			// ElectronicsService.insert(electronics);

			int result = MflpBookService.bookInsert(book);
			if (result > 0) {
				request.setAttribute("tabState", 2);
				request.setAttribute("successMsg", "도서등록완료");
				response.sendRedirect("main.jsp?contentPage=/admin/adminPage.jsp");
				return;
			} else {
				request.setAttribute("tabState", 2);
				request.setAttribute("errorMsg", "실패했습니다.");
				response.sendRedirect("main.jsp?contentPage=/admin/adminPage.jsp");
			}

		} catch (Exception e) {
			request.setAttribute("tabState", 2);
			request.setAttribute("errorMsg", "실패했습니다.");
			response.sendRedirect("main.jsp?contentPage=/admin/adminPage.jsp");

		}
	}

}
