package com.riptide.ddplatform;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringBootScrewApplicationTests { 
    @Autowired
    private ApplicationContext applicationContext;
    @Test
    void contextLoads() { 
        DataSource dataSourceMysql = applicationContext.getBean(DataSource.class);
        // 1.生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir("./file")
                // 打开目录
                .openOutputDir(false)
                // 文件类型(目前支持 HTML、WORD、MD 格式，个人建议使用html)
//                .fileType(EngineFileType.HTML)
 .fileType(EngineFileType.WORD)
// .fileType(EngineFileType.MD)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();
        // 2.生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                .version("1.0.0")
                .description("数据库表")
                .dataSource(dataSourceMysql)
                .engineConfig(engineConfig)
//                .produceConfig(getProcessConfig())
                .build();
        // 3.执行生成
        new DocumentationExecute(config).execute();
    }
//    /** * 配置想要忽略的表 * @return 生成表配置 */
//    public static ProcessConfig getProcessConfig(){
//
//        // 忽略表名，需要忽略的表将表名放到list中即可
//        List<String> ignoreTableName = Arrays.asList("aa","bb");
//        // 忽略表前缀，如忽略a开头的数据库表
//        List<String> ignorePrefix = Arrays.asList("a_","b_");
//        // 忽略表后缀
//        List<String> ignoreSuffix = Arrays.asList("_copy","_bak");
//        return ProcessConfig.builder()
//                //忽略表名
//                .ignoreTableName(ignoreTableName)
//                //忽略表前缀
//                .ignoreTablePrefix(ignorePrefix)
//                //忽略表后缀
//                .ignoreTableSuffix(ignoreSuffix)
//                .build();
//    }
}