package uploadFiles;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoad extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //得到文件名
        String uuidFileName = request.getParameter("filename");
        uuidFileName = new String(uuidFileName.getBytes("ISO-8859-1"),"UTF-8");//方法会根据指定的decode编码返回某字符串在该编码下的byte数组表示

        //存在哪里
        String realPath = getServletContext().getRealPath("/WEB-INF/files");
        String childDirectory = genChildDirectory(realPath,uuidFileName);

        String fullPath = realPath+File.separator+childDirectory+File.separator+uuidFileName;

        //告知客户端以下载的方式
        String oldFilename = uuidFileName.substring(uuidFileName.indexOf("_")+1);
        //也就是告诉用户一下载的方式去下载
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(oldFilename, "UTF-8"));

        InputStream in = new FileInputStream(fullPath);
        OutputStream out = response.getOutputStream();
        int len = -1;
        byte b[] = new byte[1024];
        while((len=in.read(b))!=-1){
            out.write(b, 0, len);
        }
        in.close();

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);

    }

    private String genChildDirectory(String realPath, String fileName) {
        int hashCode = fileName.hashCode();
        int dir1 = hashCode&0xf;
        int dir2 = (hashCode&0xf0)>>4;

        String str = dir1+File.separator+dir2;

        File file = new File(realPath,str);
        if(!file.exists()){
            file.mkdirs();
        }

        return str;

    }
}