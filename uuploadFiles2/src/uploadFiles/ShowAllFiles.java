package uploadFiles;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllFiles extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //得到所有可以下载的文件名字
        String realPath = getServletContext().getRealPath("/WEB-INF/files");
        //key:UUID文件名；value：老文件名
        Map<String, String> map = new HashMap<String, String>();
        File file = new File(realPath);
        //进行递归的遍历
        treeWalk(file,map);

        request.setAttribute("map", map);
        request.getRequestDispatcher("/listFiles.jsp").forward(request, response);



    }

    //递归遍历，得到最终的文件
        private void treeWalk(File file, Map<String, String> map) {
            if(file.isFile()){
                //得到文件的全名，由于文件全名在上传的时候存在一个hashcode，所以还需要进行修剪
                String guidFileName = file.getName();// LKSDJFLKDSKF_a.txt
                String oldFileName = guidFileName.substring(guidFileName.indexOf("_")+1);// a.txt
                map.put(guidFileName, oldFileName);
            }else{
                File files[] = file.listFiles();
                for(File f:files){
                    treeWalk(f,map);
                }
            }
        }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }

}

