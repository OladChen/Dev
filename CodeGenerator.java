package com.shinfo.data.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!StringUtils.isEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        // TODO 设置用户名
        gc.setAuthor("zhg");
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        //是否生成完成后打开资源管理器
        //日期类型的字段使用哪个类型，默认是 java8的 日期类型，此处改为 java.util.date
        //gc.setDateType(DateType.ONLY_DATE);
        //是否覆盖 已存在文件，默认 false 不覆盖
        gc.setFileOverride(false);
        //mapper.xml 是否生成 ResultMap，默认 false 不生成
        gc.setBaseResultMap(false);
        //mapper.xml 是否生成 ColumnList，默认 false 不生成
        gc.setBaseColumnList(false);
        gc.setOpen(false);
        //生成的实体类名字，增加前后缀的 ，下面的mapper xml 同理,另外还有controller和service的名称配置
//        globalConfig.setEntityName("%sEntity");
        // service 命名方式
        gc.setServiceName("I%sService");
        // service impl 命名方式
        gc.setServiceImplName("%sServiceImpl");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(false);
        mpg.setGlobalConfig(gc);

        // TODO 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://10.212.167.91:3306/js?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&autoReconnect=true&useSSL=false");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("js");
        dsc.setPassword("Jspush@123!");
        mpg.setDataSource(dsc);

        // TODO 包配置
        PackageConfig pc = new PackageConfig();
        //通过控制台输入
        //pc.setModuleName(scanner("模块名"));
        pc.setParent("com.shinfo.data");
        pc.setEntity("model");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        //TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
        //TableFill modifiedField = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        //tableFillList.add(createField);
        //tableFillList.add(modifiedField);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                // TODO 指定mapper的xml生成的路径，生成的名字可以修改
                return projectPath + "/src/main/java/com/shinfo/data/mapper/mapping/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //是否启用 builder 模式 例：new DevDevice().setDealerId("").setDeviceCode("");
        //strategy.setEntityBuilderModel(true);
        // 公共父类
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 设置逻辑删除键
        strategy.setLogicDeleteFieldName("is_del");
        // TODO 指定生成的bean的数据库表名
        strategy.setInclude("T_PLANT_MONITOR");
        //控制台输入
        //strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //strategy.setSuperEntityColumns("id");
        //  被正则过滤的表会提示 “^dev_.*$” 表不存在，其实需要生成代码的表已经正常生成完毕了。
        //strategy.setInclude("^dev_.*$");
        //  不需要生成的表
        //  strategy.setExclude("sequence");
        //生成的字段 是否添加注解，默认false
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表前缀，配置后 生成的的代码都会把前缀去掉
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}