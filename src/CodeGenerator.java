import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Formatter;

/**
 * Class takes care of generating the preview and sourceCode to be saved to file.
 * Preview and soruceCode is generated by the data from the komponent object.
 * That is passed by functions from tableModel object.
 */
public class CodeGenerator {

	private Formatter output;
	private FileChooser file;
	
	private String[] TYPE = new String[]{"JLabel", "JTextField", "JTextArea", "JButton"};
	private String[] FORANKRING = new String[]{"CENTER", "NORTH", "NORTHEAST", "EAST", "SOUTHEAST", "SOUTH", "SOUTHWEST", "WEST", "NORTHWEST"};
    private String[] SKALERING = new String[]{"NONE", "HORIZONTAL", "VERTICAL", "BOTH"};
	
    /**
     * Initialize FileChooser object
     */
	public CodeGenerator() {
		file = new FileChooser();
	}

	/**
	 * Gets the choosen path from the user so data can be saved to file.
	 * Takes tablemodel to pass along to javaSourceCodeToFile function. 
	 * @param tablemodel
	 */
	public void openSourceFile(TableModel tm) {
		Path p = file.saveLayoutAtPath();
		if(p != null){
			try {
				output = new Formatter(p.toString());
			} catch (SecurityException se) {
				System.err.println("Write permisson denied.");
			} catch (FileNotFoundException f) {
				System.err.println("Error opening file");
			}
			javaSourceCodeToFile(tm);
		}
	}
	
	/**
	 * Writes hard coded output to the file, with data from Komponent.
	 * @param tablemodel object
	 */
	public void javaSourceCodeToFile(TableModel tm) {
		output.format("import javax.swing.*;"
					+ "\nimport java.awt.*;"
					+ "\n/**\n* Code generated from GridBagLayout Editor\n*/" 
					+ "\npublic class "+file.returnPathName()+" extends JPanel {");
					typeCreation(tm);
		output.format("\n\n\tpublic "+file.returnPathName()+" () {"
					+ "\n\t  GridBagLayout layout = new GridBagLayout();" 
					+ "\n\t  GridBagConstraints gbc = new GridBagConstraints();"
					+ "\n\t  setLayout(layout);"); 
					constraints(tm);
		output.format("\n\t}"
					+ "\n\n\tstatic public void main(String[] args) {" 
					+ "\n\t  JFrame frame = new JFrame(\"Simple Stuff\");"
					+ "\n\t  "+file.returnPathName()+" panel = new "+file.returnPathName()+"();" 
					+ "\n\t  frame.add(panel, BorderLayout.CENTER);"
					+ "\n\t  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);" 
					+ "\n\t  frame.setSize(800, 300);"
					+ "\n\t  frame.setVisible(true);" 
					+ "\n  }"
					+ "\n}" 
					+ ""
					+ "" 
					+ ""
					+ "");
	}
	
	/**
	 * Gets the choosen path from the user so data can be saved to file.
	 * Takes tablemodel to pass along to javaPreviewToFile function. 
	 * @param tablemodel
	 */
	public void openPreviewFile(TableModel tm) {
		Path p = file.saveLayoutAtPath();
		if(p != null){
			try {
				output = new Formatter(p.toString());
			} catch (SecurityException se) {
				System.err.println("Write permisson denied.");
			} catch (FileNotFoundException f) {
				System.err.println("Error opening file");
			}
			javaPreviewToFile(tm);
		}
	}
	/**
	 * Writes hard coded output to the file, with data from Komponent.
	 * @param tablemodel
	 */
	public void javaPreviewToFile(TableModel tm) {
		output.format("import javax.swing.*;"
					+ "\nimport java.awt.*;"
					+ "\n/**\n* Code generated from GridBagLayout Editor\n*/" 
					+ "\npublic class "+file.returnPathName()+" extends JPanel {");
					typeCreation(tm);
		output.format("\n\n\tpublic "+file.returnPathName()+" () {"
					+ "\n\t  GridBagLayout layout = new GridBagLayout();" 
					+ "\n\t  GridBagConstraints gbc = new GridBagConstraints();"
					+ "\n\t  setLayout(layout);"); 
					constraints(tm);
		output.format("\n}" ); 
	}
	
	/**
	 * Checks the tabel object types and writes the proper type to file
	 * @param tabelmodel
	 */
	public void typeCreation(TableModel tm) {
		for (int i=0; i<tm.getRowCount(); i++) {
			
			if (TYPE[0] == TYPE[(int) tm.getValueAt(i, 0)]) {
				output.format("\n\tJLabel "+tm.getValueAt(i, 1)+" = new JLabel (\""+tm.getValueAt(i, 2)+"\");");
			}
			if (TYPE[1] == TYPE[(int) tm.getValueAt(i, 0)]) {
				output.format("\n\tJTextField "+tm.getValueAt(i, 1)+" = new JTextField (\""+tm.getValueAt(i, 2)+"\", "+tm.getValueAt(i, 10)+");");
			}
			if (TYPE[2] == TYPE[(int) tm.getValueAt(i, 0)]) {
				output.format("\n\tJTextArea "+tm.getValueAt(i, 1)+" = new JTextArea (\""+tm.getValueAt(i, 2)+"\", " +tm.getValueAt(i, 9)+"," +tm.getValueAt(i, 10)+ ");");
			}
			if (TYPE[3] == TYPE[(int) tm.getValueAt(i, 0)]) {
				output.format("\n\tJButton "+tm.getValueAt(i, 1)+" = new JButton (\""+tm.getValueAt(i, 2)+"\");");
			}
		}
	}
	
	/**
	 * Writes the correct constraints for the given type to file
	 * @param tabelmodel
	 */
	public void constraints(TableModel tm) {
    	String t="4";
		for (int i=0; i<tm.getRowCount(); i++) {
			if (t.equals(tm.getValueAt(i, 0))) {
				output.format(""
						+ "\n\t  gbc.gridx = "+tm.getValueAt(i, 4)+";"
						+ "\n\t  gbc.gridy = "+tm.getValueAt(i, 3)+";" 
						+ "\n\t  gbc.gridwidth = "+tm.getValueAt(i, 6)+";"
						+ "\n\t  gbc.gridheight = "+tm.getValueAt(i, 5)+";" 
						+ "\n\t  gbc.anchor = java.awt.GridBagConstraints." + FORANKRING[(int) tm.getValueAt(i, 8)]+";"
						+ "\n\t  gbc.fill = java.awt.GridBagConstraints." + SKALERING[(int) tm.getValueAt(i, 7)]+";"
						+ "\n\t  JScrollPane " +tm.getValueAt(i, 1)+"JScrollPane = new JScrollPane ("+tm.getValueAt(i, 1)+");"
						+ "\n\t  layout.setConstraints ("+tm.getValueAt(i, 1)+"ScrollPane, gbc);"
						+ "\n\t  add ("+tm.getValueAt(i, 1)+"ScrollPane);" 
						+ "\n\t  "+tm.getValueAt(i, 1)+".setLineWrap (true);"
						+ "\n\t  "+tm.getValueAt(i, 1)+".setWrapStyleWord (true);");
			} else {
				output.format(""
						+ "\n\t  gbc.gridx = "+tm.getValueAt(i, 4)+";"
						+ "\n\t  gbc.gridy = "+tm.getValueAt(i, 3)+";" 
						+ "\n\t  gbc.gridwidth = "+tm.getValueAt(i, 6)+";"
						+ "\n\t  gbc.gridheight = "+tm.getValueAt(i, 5)+";" 
						+ "\n\t  gbc.anchor = java.awt.GridBagConstraints." + FORANKRING[(int) tm.getValueAt(i, 8)]+";"
						+ "\n\t  gbc.fill = java.awt.GridBagConstraints." + SKALERING[(int) tm.getValueAt(i, 7)]+";"
						+ "\n\t  layout.setConstraints ("+tm.getValueAt(i, 1)+", gbc);"
						+ "\n\t  add ("+tm.getValueAt(i, 1)+");" );
			}
		}
	}
	/**
	 * Checks if there is a file to close
	 */
	public void closeFile() {
		if (output != null)
			output.close();
	}
}
