package board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.service.BoardServiceImpl;
import board.service.BoardService;
import board.vo.Attach;
import board.vo.Board;
import lombok.Getter;
import util.Common;

@WebServlet("/board/write")
public class Write extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "글쓰기");
		req.getRequestDispatcher("../WEB-INF/jsp/board/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//첨부 파일 파트
		String uploadPath = req.getRealPath("/upload");
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		uploadPath = uploadPath + File.separator + today;
		File file = new File(uploadPath);
			
		if(!file.exists()){
			file.mkdirs();
		}
		//upload/210322/
		int maxSize = 10*1024*1024;
			
		UUIDFileRenamePolicy policy = new UUIDFileRenamePolicy();
		
		MultipartRequest multi = new MultipartRequest(req, uploadPath, maxSize, "utf-8", policy);
		
		String[] names = {"file1", "file2", "fiel3"};
		
		List<Attach> attachs = new ArrayList<Attach>();
		int idx = 0;
		for(String name : names) {
			
			String fileSystemName = multi.getFilesystemName(name);
			String originName = multi.getOriginalFileName(name);
			
			if(fileSystemName == null) {
				continue;
			}
			Attach attach = new Attach(fileSystemName, today, originName, isImage(multi.getContentType(name)), idx++, null);
			attachs.add(attach);	
		}
		
		
		//타이틀, 컨텐트, 카테고리, 작성자, 
		
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		int category = Common.getDefaultValue(multi.getParameter("category"));
		
		if(Common.getSessionMember(req) == null) {
			resp.sendRedirect(req.getContextPath() +"/index");
			return;
		}
		String userid = Common.getSessionMember(req).getUserid();
		
		
		Board board = new Board(title, content, userid, null,category);
		BoardService service = new BoardServiceImpl();
		board.setAttachs(attachs);
		service.write(board);
		
		resp.sendRedirect("list?category=" + category);
	}
	
//	private String getExt(String fileName) {
//		String str = null;
//		if(fileName != null && fileName.lastIndexOf(".") != -1) {
//			str = fileName.substring(fileName.lastIndexOf(".")+1);				
//		}
//		return str;
//	}
	
	private boolean isImage(String type) {
		
		return !Objects.isNull(type) && type.contains("image");
	}
	
	@Getter
	public class UUIDFileRenamePolicy implements FileRenamePolicy {
			private String ext;
			@Override
		    public File rename(File f) {
		        String name = f.getName();
		        String body = null;
		        
		        int dot = name.lastIndexOf(".");
		        if (dot != -1) {
		            ext = name.substring(dot); // includes "."
		        } else {
		            ext = "";
		        }
		        UUID uuid = UUID.randomUUID();
		        body = uuid.toString();
		 
		        File renameFile = new File( f.getParent(), body + ext );
		       
		        f.renameTo(renameFile);
		        return renameFile;  
		    }
	}
	

}
