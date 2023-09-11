package ltd.fdsa.code.model;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Schema implements Serializable {
    String name;
    String description;
    List<Entity> entities;
    List<Association> relations;
    /*扩展*/
    String input;
    String output;
    String template;
    String setting;

    Schema(String name, String description, List<Entity> entities, List<Association> relations, String inputFolder, String output, String template, String setting) {
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
        private List<Entity> entities;
        private List<Association> relations;
        @Getter
        private String inputFolder;
        @Getter
        private String outputFolder;
        @Getter
        private String templateFolder;
        @Getter
        private String settingFolder;

        ModuleBuilder() {
        }

        public ModuleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ModuleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ModuleBuilder entities(List<Entity> entities) {
            this.entities = entities;
            return this;
        }

        public ModuleBuilder relations(List<Association> relations) {
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

        public Schema build() {
            return new Schema(name, description, entities, relations, inputFolder, outputFolder, templateFolder, settingFolder);
        }

        public String toString() {
            return "Module.ModuleBuilder(name=" + this.name + ", description=" + this.description + ", entities=" + this.entities.stream().map(Entity::toString).collect(Collectors.joining(",")) + ", relations=" + this.relations.stream().map(Association::toString).collect(Collectors.joining(",")) + ", inputFolder=" + this.inputFolder + ", outputFolder=" + this.outputFolder + ", templateFolder=" + this.templateFolder + ", settingFolder=" + this.settingFolder + ")";
        }
    }
}
