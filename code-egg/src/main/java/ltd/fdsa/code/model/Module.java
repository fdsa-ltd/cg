package ltd.fdsa.code.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Module implements Serializable {
    String name;
    String description;
    Entity[] entities;
    Association[] relations;
    /*扩展*/
    String input;
    String output;
    String template;
    String setting;

    Module(String name, String description, Entity[] entities, Association[] relations, String inputFolder, String output, String template, String setting) {
        this.name = name;
        this.description = description;
        this.entities = entities;
        this.relations = relations;
        this.input = inputFolder;
        this.output = output;
        this.template = template;
        this.setting = setting;
    }

    public static ModuleBuilder builder() {
        return new ModuleBuilder();
    }

    public static class ModuleBuilder {
        private String name;
        private String description;
        private Entity[] entities;
        private Association[] relations;
        private String inputFolder;
        private String outputFolder;
        private String templateFolder;
        private String settingFolder;

        ModuleBuilder() {
        }

        public String getSettingFolder() {
            return settingFolder;
        }

        public String getInputFolder() {
            return inputFolder;
        }

        public String getOutputFolder() {
            return outputFolder;
        }

        public String getTemplateFolder() {
            return templateFolder;
        }

        public ModuleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ModuleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ModuleBuilder entities(Entity[] entities) {
            this.entities = entities;
            return this;
        }

        public ModuleBuilder relations(Association[] relations) {
            this.relations = relations;
            return this;
        }

        public ModuleBuilder inputFolder(String inputFolder) {
            this.inputFolder = inputFolder;
            return this;
        }

        public ModuleBuilder outputFolder(String outputFolder) {
            this.outputFolder = outputFolder;
            return this;
        }

        public ModuleBuilder templateFolder(String templateFolder) {
            this.templateFolder = templateFolder;
            return this;
        }

        public ModuleBuilder settingFolder(String settingFolder) {
            this.settingFolder = settingFolder;
            return this;
        }

        public Module build() {
            return new Module(name, description, entities, relations, inputFolder, outputFolder, templateFolder, settingFolder);
        }

        public String toString() {
            return "Module.ModuleBuilder(name=" + this.name + ", description=" + this.description + ", entities=" + java.util.Arrays.deepToString(this.entities) + ", relations=" + java.util.Arrays.deepToString(this.relations) + ", inputFolder=" + this.inputFolder + ", outputFolder=" + this.outputFolder + ", templateFolder=" + this.templateFolder + ", settingFolder=" + this.settingFolder + ")";
        }
    }
}
