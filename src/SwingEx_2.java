import java.awt.*;  // Using Frame class in package java.awt
import java.awt.event.*;
//https://www.ntu.edu.sg/home/ehchua/programming/java/J4a_GUI.html

// A GUI program is written as a subclass of Frame - the top-level container
// This subclass inherits all properties from Frame, e.g., title, icon, buttons, content-pane

/*
 * Three steps are necessary to create and place a GUI component:

1) Declare the component with an identifier (name);
2) Construct the component by invoking an appropriate constructor via the new operator;
3) Identify the container (such as Frame or Panel) designed to hold this component. 
   The container can then add this component onto itself via aContainer.add(aComponent) method. 
   Every container has a add(Component) method. Take note that it is the container that 
   actively and explicitly adds a component onto itself, NOT the other way.
 */


public class SwingEx_2 extends Frame implements ActionListener, WindowListener {

   // private variables
	Label myLabel;
	Button myButton;
	TextField myTextField;
	private int count = 0;
	
	
   // Constructor to setup the GUI components
   public SwingEx_2() {  
	   setLayout(new FlowLayout());
	   myTextField = new TextField(count + "", 10);//init text, columns
	   add(myTextField);
	   myLabel = new Label("cool label", Label.RIGHT);
	   add(myLabel);
	   myButton = new Button("cool button");
	   add(myButton);
	   
	   //source: button adds listener via "this" which implements ActionListener and is upcasted
	   myButton.addActionListener(this);
	   //upon button click, an event object is created which will be passed to
	    // "btnCount" is the source object that fires an ActionEvent when clicked.
	    // The source add "this" instance as an ActionEvent listener, which provides
	    //   an ActionEvent handler called actionPerformed().
	    // Clicking "btnCount" invokes actionPerformed().
	  
	   //we can add to a list of listener objects
	   myTextField.addActionListener(this);
	   // Hitting "enter" on tfInput invokes actionPerformed().
	   
	   addWindowListener(this);
	   // "super" Frame (source object) fires WindowEvent.
       // "super" Frame adds "this" object as a WindowEvent listener.
	   
	   setTitle("cool title");
	   setSize(250,100);
	   
	   setVisible(true);
	   
	   //inspect elements
	   System.out.println(this);
	   System.out.println(myLabel);
	   System.out.println(myButton);
   }

   // methods


   // The entry main() method
   public static void main(String[] args) {
      // Invoke the constructor (to setup the GUI) by allocating an instance
      new SwingEx_2();
   }
   
// ActionEvent handler - Called back upon button-click.
   //also known as a call back method
   @Override
   public void actionPerformed(ActionEvent evt) {
	  String input = myTextField.getText();
      System.out.println("myTextField: " + myTextField.getText());
      if(input.equals("doggo")) {
    	  System.out.println("you can haz doggo");
      } 
	   
      ++count; // Increase the counter value
      // Display the counter value on the TextField tfCount
      System.out.println(count);
      myTextField.setText(count + ""); // Convert int to String
      if(count == 10) {
    	  System.out.println("you win!");
      }
   }
   /* WindowEvent handlers */
   // Called back upon clicking close-window button
   @Override
   public void windowClosing(WindowEvent evt) {
      System.exit(0);  // Terminate the program
   }
 
   // Not Used, BUT need to provide an empty body to compile.
   @Override public void windowOpened(WindowEvent evt) { }
   @Override public void windowClosed(WindowEvent evt) { }
   // For Debugging
   @Override public void windowIconified(WindowEvent evt) { System.out.println("Window Iconified"); }
   @Override public void windowDeiconified(WindowEvent evt) { System.out.println("Window Deiconified"); }
   @Override public void windowActivated(WindowEvent evt) { System.out.println("Window Activated"); }
   @Override public void windowDeactivated(WindowEvent evt) { System.out.println("Window Deactivated"); }
}