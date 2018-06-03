import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ChangingEmailScreen extends JFrame {
	private JFrame frame;
	public ChangingEmailScreen() {
		frame = new JFrame();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - 700) / 2);
	    int y = (int) ((dimension.getHeight() - 500) / 2);
		JPanel panel = new JPanel();
		 
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
 
		panel.setLayout(new GridLayout(x, y, 50, 50));
	     
		ConnectSQL player = new ConnectSQL();
		String username =  Login.Username();
		JButton btnBack = new JButton();
		//btnLogout.setIcon(loginImage);
		btnBack.setSize(60,140);
		btnBack.setText("Back");
		btnBack.setVisible(true);
		btnBack.setBounds(0, 290, 140, 50);
		/* JLabel lblABnumber = new JLabel("Your Bank Number : " + String.valueOf(player.getAccountBankNumber(username.trim())));
	        lblABnumber.setSize(220, 50);
	        lblABnumber.setBounds(0, 40, 220, 50);
	        lblABnumber.setVisible(true); 
	        add(lblABnumber);
	        
	        JLabel lblEmail = new JLabel("Your Email : " + String.valueOf(player.getEmail(username.trim())));
	        lblEmail.setSize(220, 50);
	        lblEmail.setBounds(0, 60, 220, 50);
	        lblEmail.setVisible(true); 
	        add(lblEmail); */
		add(btnBack);
		btnBack.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
					if(player.checkInternet() == true)	
					{
						   dispose();
					      Home nextframe= new Home();
	                      nextframe.setVisible(true);
		            }
	                      else
	  					{
	  						JOptionPane.showMessageDialog(null, "lost internet connection, please check your internet!");
	  						  dispose();
	  					      Login nextframe= new Login();
	  	                      nextframe.setVisible(true);
	  					}
		            } 
		         } 
		      ); 
		
		
		JButton btnAssignRegister = new JButton();
		btnAssignRegister.setSize(60,140);
		btnAssignRegister.setText("Change");
		btnAssignRegister.setVisible(true);
		btnAssignRegister.setBounds(0, 240, 140, 50);
		
		add(btnAssignRegister);
		//String username =  Login.Username();
	/*	JLabel lblCurrentpassword = new JLabel("Enter current password :");
		lblCurrentpassword.setSize(120, 25);
		lblCurrentpassword.setBounds(40, 40, 160, 30);
		lblCurrentpassword.setVisible(true); 
        add(lblCurrentpassword);
        
        JPasswordField currentpassword = new JPasswordField();
        currentpassword .setSize(140, 25);
        currentpassword .setBounds(220, 40, 120, 30);
        currentpassword .setVisible(true);
		add(currentpassword );
	*/	
		JLabel lblPassword = new JLabel("Enter password       :");
		lblPassword.setSize(120, 25);
		lblPassword.setBounds(40, 80, 160, 30);
		lblPassword.setVisible(true); 
		add(lblPassword);
		
		JPasswordField pW = new JPasswordField();
		pW.setSize(140, 325);
		pW.setBounds(220, 80, 120, 30);
		pW.setVisible(true);
		add(pW);
        
		JLabel lblEmail = new JLabel("Enter new email :");
		lblEmail.setSize(120, 25);
		lblEmail.setBounds(40, 120, 160, 30);
		lblEmail.setVisible(true); 
		add(lblEmail);
		
		JTextField Email = new JTextField();
		Email.setSize(140, 325);
		Email.setBounds(220, 120, 120, 30);
		Email.setVisible(true);
		add(Email);
    JPanel southJPanel = new JPanel(new GridLayout(2, 3, 5, 10));
    	
    	JPanel southJPanelsub = new JPanel(new BorderLayout());
    	
    	add(southJPanelsub,BorderLayout.SOUTH);
		southJPanelsub.add(southJPanel, BorderLayout.WEST);
		// preference nút contact với southJPanelsub
		southJPanelsub.setPreferredSize(new Dimension(100, 100));
		southJPanelsub.setMinimumSize(new Dimension(100, 100));
		southJPanel.add(btnAssignRegister );
		southJPanel.add(btnBack );
		add(panel);
		btnAssignRegister.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
			@Override
			public void actionPerformed(ActionEvent e) {
				if(player.checkInternet() == true )
	              {
				if (pW.getText().isEmpty() )  
            	{
            		System.out.println("blank");
					JOptionPane.showMessageDialog(null, "Your password is blank");
            	}
            	else if (Email.getText().isEmpty() )  
            	{
            		System.out.println("blank");
					JOptionPane.showMessageDialog(null, "Your new email is blank");
            	}
            	else if (Email.getText().toString().length() > 30 )  
            	{
            		JOptionPane.showMessageDialog(null, "Your new email have to less than 30 characters");
            	}
            	else if  (Email.getText().toString().contains("@") == false || Email.getText().toString().contains(".") == false)
            	{
            		JOptionPane.showMessageDialog(null, "Your new email is invalid");
            	}
             	else if  (pW.getText().toString().trim().equals(player.getPassword(username)) == false)
            	{
            		System.out.println("blank");
        			JOptionPane.showMessageDialog(null, "Your  password is not  match with current password");
            	} 
             	else if (Email.getText().toString().trim().equals(player.getEmail(username)) == true)
             	{
             		JOptionPane.showMessageDialog(null, "Your  email is same with current email");
             	}
				else
				{
				int reply = JOptionPane.showConfirmDialog(
			            null,
			            "Are you sure?",
			            "Confirm",
			            JOptionPane.YES_NO_OPTION);
        
			        if(reply == JOptionPane.YES_OPTION){
			        	   if(player.checkInternet() == true )
				              {
				           if(player.checkEmail(Email.getText().toString().trim()) == false)
				           {	
						  player.setEmail(username, Email.getText().toString().trim());
						  JOptionPane.showMessageDialog(null, "You have changed your email succesfully,you have to login again!");
						  dispose();
						  Home ex = new Home();
							ex.setVisible(true);
				           }
				           else
				           {
				        	   JOptionPane.showMessageDialog(null, "Your email is already registered");
				           }
								
								}
				            else
				            {
				            	JOptionPane.showMessageDialog(null, "Please check your internet connection");
				            }
				            
			        }
				}
			}
				else
	            {
	            	JOptionPane.showMessageDialog(null, "Please check your internet connection");
	            	  dispose();
				      Login nextframe= new Login();
                      nextframe.setVisible(true);
	            }
		         }
			
		         }
		         );
		setTitle("Changing your email");
		setSize(ResolutionSetting.x, ResolutionSetting.y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
