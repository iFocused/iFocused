package application.usecases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileEditor {
	private File hostfileFile;

	public FileEditor(File hostfileFile) {
		this.hostfileFile = hostfileFile;
	}

	public boolean blockWebsite(String websiteURL) {
		try {
			System.out.println(this.hostfileFile.getAbsolutePath());
			this.hostfileFile.setWritable(true);
			FileWriter myWriter = new FileWriter(this.hostfileFile.getAbsolutePath(), true);
			myWriter.write("\n127.0.0.1 " + websiteURL);
			myWriter.close();

			System.out.println("Successfully wrote to the file.");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean unblockWebsite(String websiteURL) {
		try {
			this.hostfileFile.setWritable(true);
			BufferedReader file = new BufferedReader(new FileReader(this.hostfileFile));
			String tmpBuffer;
			String finalData = "";
			while ((tmpBuffer = file.readLine()) != null) {
				if (tmpBuffer.equals("127.0.0.1 " + websiteURL)) {
					continue;
				}

				finalData += tmpBuffer + "\n";
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(this.hostfileFile.getAbsoluteFile()));
			writer.write(finalData, 0, finalData.length() - 1);
			file.close();
			writer.close();
			return true;

		} catch (IOException e) {
			System.err.println("an error has occurred");
		}
		return false;
	}
}
