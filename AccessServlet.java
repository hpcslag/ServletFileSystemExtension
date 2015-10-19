import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AccessFile", urlPatterns = {"/AccessFile/*"})
public class AccessServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String path = getServletContext().getRealPath("/"+request.getPathInfo().substring(1));
        File f = new File(path);
        response.setHeader("Content-Length", String.valueOf(f.length()));
        response.setContentType(getServletContext().getMimeType(f.getName()));
        
        ServletOutputStream out;  
        out = response.getOutputStream();  
        FileInputStream fin = new FileInputStream(path);
        BufferedInputStream bin = new BufferedInputStream(fin);  
        BufferedOutputStream bout = new BufferedOutputStream(out);  
        int ch =0; ;  
        while((ch=bin.read())!=-1){  
            bout.write(ch);  
        }
        
        bin.close();  
        fin.close();  
        bout.close();  
        out.close();  
    }
}
