import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import lombok.Getter;

@WebServlet("/upload")
public class UploadController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("uploadSample.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploadPath = req.getRealPath("/upload");
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		uploadPath = uploadPath + File.separator + today;
		File file = new File(uploadPath);

		if (!file.exists()) {
			file.mkdirs();
		}
		// upload/210322/
		int maxSize = 10 * 1024 * 1024;

		UUIDFileRenamePolicy policy = new UUIDFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(req, uploadPath, maxSize, "utf-8", policy);

		String fileSystemName = multi.getFilesystemName("file1");
		String originName = multi.getOriginalFileName("file1");
		String ext = policy.ext;

		System.out.println(fileSystemName);
		System.out.println(originName);
		System.out.println(ext);
		System.out.println(today);
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

			File renameFile = new File(f.getParent(), body + ext);

			f.renameTo(renameFile);
			return renameFile;

		}
	}
}
