package reduce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.coody.framework.core.util.file.FileUtils;

public class Test {

	public static void main(String[] args) throws IOException {
		String[] txt = FileUtils.readString("d://1.txt").split("\r\n");
		for (String string : txt) {
			String[] attrs = string.split(" ");
			String fileName = attrs[attrs.length - 1];
			File file = new File("E:\\hx1\\" + fileName);
			if (file.exists()) {
				continue;
			}
			file.mkdir();
			for (File child : new File("E:\\hx1\\628").listFiles()) {
				System.out.println(child.getPath().replace("628", fileName));
				copyFile(child.getPath(), child.getPath().replace("628", fileName));
			}
		}
	}

	public static void copyFile(String oldPath, String newPath) throws IOException {
		File oldFile = new File(oldPath);
		File file = new File(newPath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(file);
		;

		byte[] buffer = new byte[1024];
		int size;
		while ((size=in.read(buffer)) != -1) {
			out.write(buffer,0,size);
		}
	}
}
