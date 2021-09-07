/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anpdt.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author ASUS
 */
public class MyServletListener implements ServletContextListener {

    Map<String, String> roadmap= new HashMap<>();

    public void readRoadMapFromFile(String path) {
        try {
            File f = new File(path);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                int index = line.lastIndexOf("=");
                String servletName = line.substring(0,index);
                String url = line.substring(index+1);
                roadmap.put(servletName, url);
            }
            br.close();
            fr.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/");
        String txtFile = realPath+"WEB-INF/roadmap.txt";
        readRoadMapFromFile(txtFile);
        context.setAttribute("ROADMAP", roadmap);
        System.out.println("real "+txtFile);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       
    }
}
