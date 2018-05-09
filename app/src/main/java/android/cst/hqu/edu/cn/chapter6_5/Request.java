package android.cst.hqu.edu.cn.chapter6_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Request {

    public static String Get(String url,String params){
        String result="";
        BufferedReader br=null;
        String urlName=url+"?"+params;
        try {
            URL realUrl = new URL(urlName);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "---->" + map.get(key));
            }
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result += "\n" + line;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;

    }
    public static String Post(String url,String params){
        PrintWriter out=null;
        BufferedReader br=null;
        String result="";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter((conn.getOutputStream()));
            out.print(params);
            out.flush();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result += "\n" + line;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(out!=null){
                    out.close();
                }
                if(br!=null){
                    br.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

        return  result;
    }
}
