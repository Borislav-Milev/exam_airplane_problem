package softuni.exam.util.contract;

public interface XmlParser {

    <T> T parse(Class<T> objectClass, String filePath);
}
