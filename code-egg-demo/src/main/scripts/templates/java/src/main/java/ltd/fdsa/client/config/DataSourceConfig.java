package ${setting('project.package','ltd.fdsa.client')}.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@AutoConfigureBefore(DruidDataSourceAutoConfigure.class)
@Slf4j
public class DataSourceConfig {
    public static final String WRITER_DATASOURCE = "dataSource";
    public static final String READER_DATASOURCE = "readerDataSource";

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary
    public DataSource writerDataSource() {
        return new DruidDataSource();
    }


}