package com.morningsidevc.utils;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成sqlMapper和vo
 * @author float.lu
 */
public class MybatisGenerato_FloatLU {
    public static void main(String ...s){
        try{
            List<String> warnings = new ArrayList<String>();
            ConfigurationParser parser = new ConfigurationParser(warnings);
            Configuration configuration = parser.parseConfiguration(new File("/Users/andy/eir/eir-web/src/generatorConfig_floatlu.xml"));
            MyBatisGenerator g = new MyBatisGenerator(configuration,null,warnings);
            g.generate(null);
            for(String as : warnings)
                System.out.println(as);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.print("done");
    }

}
