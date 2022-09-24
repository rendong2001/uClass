package com.pdsu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyUtils {

    //获取qr对象
    public static QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
    /**
     * 转json并响应给前台
     * @param vo
     * @param resp
     */
    public static void printJson(ResultVo vo, HttpServletResponse resp) {
        String json = null;
        try {
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(vo);
            resp.getWriter().print(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}