package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class FileDownloader extends HttpServlet{

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		String realPath = arg0.getParameter("realPath");
		String fileName = arg0.getParameter("fileName");
		
		realPath = arg0.getRealPath(realPath);
		
		System.out.println(realPath);
		System.out.println(fileName);
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(realPath));
		String mimeType = getServletContext().getMimeType(realPath);
		System.out.println("mimeType >> "+ mimeType);
		if(Objects.isNull(mimeType)) mimeType = "application/octet-stream";
		
		arg1.setContentType(mimeType);
		String agent = arg0.getHeader("user-agent");
		if(agent.toLowerCase().contains("trident")) { //IE
			fileName = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
		}
		else { //IE X 
			fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		}
		
		arg1.setHeader("Content-Disposition", "attachment; fileName=\""+ fileName + "\"");
		BufferedOutputStream bos = new BufferedOutputStream(arg1.getOutputStream());
		
		int b;
		while ((b = bis.read()) != -1) {
			bos.write(b);
		}
		bos.close();
		bis.close();
	}
	
}
