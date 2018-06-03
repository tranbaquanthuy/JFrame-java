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

public class ChangingPasswordScreen extends JFrame 
{
	private JFrame frame;
	public ChangingPasswordScreen() {
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
		JLabel lblCurrentpassword = new JLabel("Enter current password :");
		lblCurrentpassword.setSize(120, 25);
		lblCurrentpassword.setBounds(40, 40, 160, 30);
		lblCurrentpassword.setVisible(true); 
        add(lblCurrentpassword);
        
        JPasswordField currentpassword = new JPasswordField();
        currentpassword .setSize(140, 25);
        currentpassword .setBounds(220, 40, 120, 30);
        currentpassword .setVisible(true);
		add(currentpassword );
		
		JLabel lblPassword = new JLabel("Enter new password       :");
		lblPassword.setSize(120, 25);
		lblPassword.setBounds(40, 80, 160, 30);
		lblPassword.setVisible(true); 
		add(lblPassword);
		
		JPasswordField pW = new JPasswordField();
		pW.setSize(140, 325);
		pW.setBounds(220, 80, 120, 30);
		pW.setVisible(true);
		add(pW);
        
		JLabel lblREPassword = new JLabel("Re-enter new password :");
		lblREPassword.setSize(120, 25);
		lblREPassword.setBounds(40, 120, 160, 30);
		lblREPassword.setVisible(true); 
		add(lblREPassword);
		
		JPasswordField rpW = new JPasswordField();
		rpW.setSize(140, 325);
		rpW.setBounds(220, 120, 120, 30);
		rpW.setVisible(true);
		add(rpW);
		
		add(panel);
		
    JPanel southJPanel = new JPanel(new GridLayout(2, 3, 5, 10));
    	
    	JPanel southJPanelsub = new JPanel(new BorderLayout());
    	
    	add(southJPanelsub,BorderLayout.SOUTH);
		southJPanelsub.add(southJPanel, BorderLayout.WEST);
		southJPanelsub.setPreferredSize(new Dimension(100, 100));
		southJPanelsub.setMinimumSize(new Dimension(100, 100));
		southJPanel.add(btnAssignRegister );
		southJPanel.add(btnBack );
		
		btnAssignRegister.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
			@Override
			public void actionPerformed(ActionEvent e) {
				if(player.checkInternet() == true )
	              {
				if(currentpassword.getText().isEmpty())
				{
					System.out.println("blank");
					JOptionPane.showMessageDialog(null, "Your current password is blank");
				}
            	else if (rpW.getText().isEmpty() )  
            	{
            		System.out.println("blank");
					JOptionPane.showMessageDialog(null, "Your password is blank");
            	}
            	else if (pW.getText().isEmpty() )  
            	{
            		System.out.println("blank");
					JOptionPane.showMessageDialog(null, "Your confirm password is blank");
            	}
            	else if (pW.getText().toString().length() > 10)
            	{
            		JOptionPane.showMessageDialog(null, "Your new password have to less than 10 characters");
            	}
            	else if (pW.getText().toString().trim().equals(rpW.getText().toString().trim()) == false)
            	{
			System.out.println("blank");
			JOptionPane.showMessageDialog(null, "Your new password is not match with confirm password");
            	}
            	else if  (pW.getText().toString().trim().equals(currentpassword.getText().toString().trim()))
            	{
            		System.out.println("blank");
        			JOptionPane.showMessageDialog(null, "Your new password is  match with current password");
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
				            	
									if(player.getPassword(username).equals(currentpassword.getText().toString().trim()))
									{
									    player.setPassword(username, pW.getText().toString().trim());
						JOptionPane.showMessageDialog(null, "You have changed your password succesfully,you have to login again!");
						  dispose();
						  Login ex = new Login();
							ex.setVisible(true);
									}
									else
									{
										System.out.println("Wrong current password");
										JOptionPane.showMessageDialog(null, "Wrong current password please check");
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
		setTitle("Changing your password");
		setSize(ResolutionSetting.x, ResolutionSetting.y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
