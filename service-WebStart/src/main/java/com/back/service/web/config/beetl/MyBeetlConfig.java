package com.back.service.web.config.beetl;

import com.ibeetl.starter.BeetlTemplateCustomize;
import com.back.service.system.bean.CompanyInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author magicHat
 */
@Configuration
public class MyBeetlConfig {

    @Value("${company.namewhole}")
    private String nameWhole;
    @Value("${company.namesmall}")
    private String nameSmall;
    @Value("${company.nameenglish}")
    private String nameEnglish;
    @Value("${company.address}")
    private String address;
    @Value("${company.telephone}")
    private String telephone;
    @Value("${company.phonenum}")
    private String phoneNum;
    @Value("${company.email}")
    private String email;

    @Bean
    public BeetlTemplateCustomize beetlTemplateCustomize(){
        return groupTemplate -> {
            //可以在此配置全部模板的共享参数，项目启动时加载，因此不应在此配置动态参数
            Map<String,Object> shared = new HashMap<>();
            shared.put("ConfigCompany",new CompanyInfo(nameWhole,nameSmall,nameEnglish,address,telephone,phoneNum,email));
            groupTemplate.setSharedVars(shared);
        };
    }
}
