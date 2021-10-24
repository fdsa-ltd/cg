package ltd.fdsa.code;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ClassLoader extends URLClassLoader {
    List<String> list;

    public ClassLoader(String... paths) {
        this(getURLs(paths), ClassLoader.class.getClassLoader());
    }

    public ClassLoader(URL[] urls, java.lang.ClassLoader parent) {
        super(urls, parent);
    }

    public List<Class<?>> loadClasses(JarEntryFilter entryFilter, ClassFilter classFilter) {
        List<Class<?>> results = new ArrayList<>();
        for (var url : this.getURLs()) {
            var file = url.getFile();
            JarFile jarFile = null;
            try {
                jarFile = new JarFile(file);
            } catch (IOException e) {
                continue;
            }
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                var entry = entries.nextElement();
                if (entryFilter.accept(entry)) {
                    var path = entry.getName();
                    path = path.substring(0, path.lastIndexOf("."));
                    path = path.replace("/", ".");

                    try {
                        var obj = this.loadClass(path);
                        if (classFilter.accept(obj)) {
                            results.add(obj);
                        }
                    } catch (ClassNotFoundException e) {
                        continue;
                    }
                }
            }
        }
        return results;
    }

    public Field[] getDeclaredFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>(16);
        while (clazz != null) {
            var fields = clazz.getDeclaredFields();
            var sss = Stream.of(fields).collect(Collectors.toList());
            fieldList.addAll(sss);
            clazz = clazz.getSuperclass();
        }
        Field[] f = new Field[fieldList.size()];
        return fieldList.toArray(f);
    }

    public List<String> jarFiles() throws IOException {
        if (this.list == null) {
            this.list = new ArrayList<>();
            JarFile jarFile = new JarFile(ClassLoader.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath());
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                var entry = entries.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                var path = entry.getName();
                list.add(path);
            }
        }
        return list;
    }

    private static URL[] getURLs(String[] paths) {
        List<String> dirs = new ArrayList<>();
        for (String path : paths) {
            dirs.add(path);
            collectDirs(path, dirs);
        }
        List<URL> urls = new ArrayList<>();
        for (String path : dirs) {
            urls.addAll(doGetURLs(path));
        }
        return urls.toArray(new URL[0]);
    }

    private static void collectDirs(String path, List<String> collector) {
        if (null == path || Strings.isNullOrEmpty(path)) {
            return;
        }

        File current = new File(path);
        if (!current.exists() || !current.isDirectory()) {
            return;
        }

        for (File child : Objects.requireNonNull(current.listFiles())) {
            if (!child.isDirectory()) {
                continue;
            }

            collector.add(child.getAbsolutePath());
            collectDirs(child.getAbsolutePath(), collector);
        }
    }

    private static List<URL> doGetURLs(final String path) {
        Assert.isTrue(!Strings.isNullOrEmpty(path), "jar包路径不能为空.");

        File jarPath = new File(path);

        Assert.isTrue(jarPath.exists() && jarPath.isDirectory(), "jar包路径必须存在且为目录.");

        /* set filter */
        FileFilter jarFilter = pathname -> pathname.getName().endsWith(".jar");

        /* iterate all jar */
        File[] allJars = new File(path).listFiles(jarFilter);
        assert allJars != null;
        List<URL> jarURLs = new ArrayList<>(allJars.length);

        for (File allJar : allJars) {
            try {
                jarURLs.add(allJar.toURI().toURL());
            } catch (Exception e) {
                log.error("系统加载jar包出错", e);
            }
        }

        return jarURLs;
    }

    private static class Assert {
        public static void isTrue(boolean expression, String message) {
            if (!expression) {
                throw new IllegalArgumentException(message);
            }
        }
    }


    @FunctionalInterface
    public interface ClassFilter {
        boolean accept(Class<?> clazz);
    }

    @FunctionalInterface
    public interface JarEntryFilter {
        boolean accept(JarEntry entry);
    }
}