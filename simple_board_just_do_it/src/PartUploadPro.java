import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;




@WebServlet("/partpro")
@MultipartConfig(location="C:\\20201230\\upload\\part", fileSizeThreshold=0)
public class PartUploadPro extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("uploadSample.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		if(req.getMethod().equals("POST")) {
//			String uploadPath = req.getRealPath("/upload");
//			String today= new SimpleDateFormat("yyMMdd").format(new Date());
//			uploadPath = uploadPath + File.separator + today;
//			File file = new File(uploadPath);
//			
//			if(!file.exists()){
//				file.mkdirs();
//			}
//			//upload/210322/
//			int maxSize = 10*1024*1024;
//			MultipartRequest multi = new MultipartRequest(req, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
//			System.out.println(uploadPath);
//		}
		
		String writer = req.getParameter("writer");
		Part part = req.getPart("file1");
		String contentDisposition = part.getHeader("content-disposition");
		String uploadFileName = getUploadFileName(contentDisposition, req);
		part.write(uploadFileName);

		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().println(uploadFileName + "업로드됨");
	}
	private String getUploadFileName(String contentDisposition, HttpServletRequest req) {
		String uploadFileName = null;
		String[] contentSplitStr = contentDisposition.split(";");
		int lastPathSeparatorIndex = 0;
		
//		lastPathSeparatorIndex = req.getHeader("user-agent").toLowerCase().contains("trident") ?  contentSplitStr[2].lastIndexOf("\\") : contentSplitStr[2].indexOf("\"");
		
		if(req.getHeader("user-agent").toLowerCase().contains("trident")) {
			 lastPathSeparatorIndex = contentSplitStr[2].lastIndexOf("\\");
			
		}
		else {
			lastPathSeparatorIndex = contentSplitStr[2].indexOf("\"");
		}
		int lastQuetosIndex = contentSplitStr[2].lastIndexOf("\"");
		uploadFileName = contentSplitStr[2].substring(lastPathSeparatorIndex+1, lastQuetosIndex);
		return uploadFileName;
	}
	

}
