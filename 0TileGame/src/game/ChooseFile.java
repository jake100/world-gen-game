package game;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooseFile extends JFileChooser
{
	public ChooseFile()
	{
		super(new File("res/save"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Map File", "dat");
		setFileFilter(filter);
	}
}
