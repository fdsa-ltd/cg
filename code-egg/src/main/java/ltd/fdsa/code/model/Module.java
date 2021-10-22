package ltd.fdsa.code.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Module implements Serializable {
    String name;
    String description;
    Entity[] entities;
    RelationDefine[] relations;
    /*扩展*/
    String jarFolder;
    String outputFolder;
    String templateFolder;

    Module(String name, String description, Entity[] entities, RelationDefine[] relations, String jarFolder, String outputFolder, String templateFolder) {
        this.name = name;
        this.description = description;
        this.entities = entities;
        this.relations = relations;
        this.jarFolder = jarFolder;
        this.outputFolder = outputFolder;
        this.templateFolder = templateFolder;
    }

    public static ModuleBuilder builder() {
        return new ModuleBuilder();
    }

    public static class ModuleBuilder {
        private String name;
        private String description;
        private Entity[] entities;
        private RelationDefine[] relations;
        private String jarFolder;
        private String outputFolder;
        private String templateFolder;

        ModuleBuilder() {
        }

        public String getJarFolder() {
            return jarFolder;
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

        public ModuleBuilder relations(RelationDefine[] relations) {
            this.relations = relations;
            return this;
        }

        public ModuleBuilder jarFolder(String jarFolder) {
            this.jarFolder = jarFolder;
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

        public Module build() {
            return new Module(name, description, entities, relations, jarFolder, outputFolder, templateFolder);
        }

        public String toString() {
            return "Module.ModuleBuilder(name=" + this.name + ", description=" + this.description + ", entities=" + java.util.Arrays.deepToString(this.entities) + ", relations=" + java.util.Arrays.deepToString(this.relations) + ", jarFolder=" + this.jarFolder + ", outputFolder=" + this.outputFolder + ", templateFolder=" + this.templateFolder + ")";
        }
    }
}
