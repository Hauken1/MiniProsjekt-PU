import java.awt.Component;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
/**
 * ComboBoxRendererType Class
 * Renders the JComboBox (list) and the cell (tablecell) for Type
 * @author Henrik Haukaas
 */
public class ComboBoxRendererType extends DefaultTableCellRenderer implements ListCellRenderer, TableCellRenderer{
//FUNKSJONER-START	
	/**
	 * Class constructor
	 */
	public ComboBoxRendererType() {
		setOpaque(true); 
	}
	//Allerede spesifisert av JavaDoc
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int n, boolean b1, boolean b2) {
		  if (b1) {
	            setBackground(list.getSelectionBackground());
	        } else {
	            setBackground(list.getBackground());
	        }
		  if ((Integer)value == 0) {
	            setText("JLabel");	//JLabel
	        } else if ((Integer)value == 1) {
	            setText("JTextField");		//JTextField
	        } else if ((Integer)value == 2) {
	            setText("JTextArea");	//JTextArea
	        } else if ((Integer)value == 3) {
	            setText("JButton");		//JButton
	        }  
		  
	        setSize(100, 20);
	        return this;
		
	}
	//Allerede spesifisert av JavaDoc
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean b1, boolean b2, int row, int col) {
		  if (b1) {
	            setBackground(table.getSelectionBackground());
	        } else {
	            setBackground(table.getBackground());
	        }
		  
		  if ((Integer)value == 0) {
	            setText("JLabel");	//JLabel
	        } else if ((Integer)value == 1) {
	            setText("JTextField");		//JTextField
	        } else if ((Integer)value == 2) {
	            setText("JTextArea");	//JTextArea
	        } else if ((Integer)value == 3) {
	            setText("JButton");		//JButton
	        }  
		  
		 return this;
	 }
//FUNKSJONER-SLUTT	 
} //SLUTT
