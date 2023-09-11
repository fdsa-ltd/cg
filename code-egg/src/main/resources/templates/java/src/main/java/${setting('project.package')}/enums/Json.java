package cn.zhumingwu.project.enums;

import cn.zhumingwu.base.config.Configuration;
import cn.zhumingwu.base.config.DefaultConfiguration;


public class Json implements Configuration {

    final Configuration configuration;

    public Json(String json) {
        this.configuration = DefaultConfiguration.fromJson(json);
    }

    @Override
    public String get(String path) {
        return this.configuration.get(path);
    }

    @Override
    public Configuration[] getConfigurations(String path) {
        return this.configuration.getConfigurations(path);
    }

    @Override
    public Configuration getConfiguration(String path) {
        return this.configuration.getConfiguration(path);
    }

    @Override
    public void set(String path, String value) {
        this.configuration.set(path, value);
    }

    @Override
    public Configuration copy() {
        return this.configuration.copy();
    }
}