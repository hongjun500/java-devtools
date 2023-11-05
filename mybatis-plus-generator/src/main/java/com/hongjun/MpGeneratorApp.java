package com.hongjun;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;


public class MpGeneratorApp {

	/**
	 * 输出文件所在目录
	 */
	public static final String OUTPUT_DIR =  System.getProperty("user.dir") + "\\mp-generator";

	public static void main(String[] args) {
		System.out.println("Hello world!");
		// 数据库连接url
		String url = "jdbc:mysql://localhost:3306/blog";
		String username = "root";
		String password = "1";


		// 全局配置
		GlobalConfig.Builder globalConfig = new GlobalConfig.Builder().
				outputDir(OUTPUT_DIR).
				author("hongjun500").
				// enableKotlin().
				// enableSwagger().
				dateType(DateType.TIME_PACK).
				commentDate("yyyy-MM-dd HH:mm:ss.SSS");
		// 包配置
		PackageConfig.Builder packageConfig = new PackageConfig.Builder()
				.parent("com.hongjun")
				// .moduleName("base")
				.entity("dataobject")
				.service("service")
				.serviceImpl("service.impl")
				.mapper("mapper")
				.xml("mapper.xml")
				.controller("controller");

		Entity.Builder entityConfig = new StrategyConfig.Builder()
				.entityBuilder()
				.addSuperEntityColumns("BaseEntity.class")
				.disableSerialVersionUID()
				.enableChainModel()
				.enableLombok()
				.enableRemoveIsPrefix()
				.enableTableFieldAnnotation()
				.enableActiveRecord()
				.versionColumnName("version")
				.versionPropertyName("version")
				.logicDeleteColumnName("deleted")
				.logicDeletePropertyName("deleteFlag")
				// .naming(NamingStrategy.no_change)
				// .columnNaming(NamingStrategy.underline_to_camel)
				.addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
				.addIgnoreColumns("age")
				.addTableFills(new Column("create_time", FieldFill.INSERT))
				.addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
				// .idType(IdType.AUTO)

				.formatFileName("%sDO");

		// 要生成的表
		String[] includeArr = new String[]{};
		// 排除的表
		String[] excludeArr = new String[]{"blog_auth"};
		StrategyConfig.Builder tableConfig = new StrategyConfig.Builder().addInclude(includeArr).addExclude(excludeArr);
		DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url, username, password).build();
		AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
		autoGenerator.global(globalConfig.build());
		autoGenerator.packageInfo(packageConfig.build());
		autoGenerator.strategy(entityConfig.build());
		autoGenerator.strategy(tableConfig.build());
		autoGenerator.execute(new FreemarkerTemplateEngine());

	}
}