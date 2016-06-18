import java.io.InputStream;

final public class ResourceLoder {

	public static InputStream load(String path)
	{
		InputStream input = ResourceLoder.class.getResourceAsStream(path);
		if(input == null)
			input = ResourceLoder.class.getResourceAsStream("/"+path);
		return input;
	}
}
