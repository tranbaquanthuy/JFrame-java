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

public class ChangingAddressScreen extends JFrame  {
	public ChangingAddressScreen() {
		JFrame frame = new JFrame();
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
        
		JLabel lblAddress = new JLabel("Enter new address :");
		lblAddress.setSize(120, 25);
		lblAddress.setBounds(40, 120, 160, 30);
		lblAddress.setVisible(true); 
		add(lblAddress);
		
		JTextField Address = new JTextField();
		Address.setSize(140, 325);
		Address.setBounds(220, 120, 120, 30);
		Address.setVisible(true);
		add(Address);
		
		add(panel);
		
        JPanel southJPanel = new JPanel(new GridLayout(2, 3, 5, 10));
    	
    	JPanel southJPanelsub = new JPanel(new BorderLayout());
    	
    	add(southJPanelsub,BorderLayout.SOUTH);
		southJPanelsub.add(southJPanel, BorderLayout.WEST);
		// preference nút contact với southJPanelsub
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
				if (pW.getText().isEmpty() )  
            	{
            		System.out.println("blank");
					JOptionPane.showMessageDialog(null, "Your password is blank");
            	}
            	else if (Address.getText().isEmpty() )  
            	{
            		System.out.println("blank");
					JOptionPane.showMessageDialog(null, "Your new Address is blank");
            	}
            	else if (Address.getText().toString().length() > 50 )  
            	{
            		JOptionPane.showMessageDialog(null, "Your new Address have to less than 50 characters");
            	}
             	else if  (pW.getText().toString().trim().equals(player.getPassword(username)) == false)
            	{
            		System.out.println("blank");
        			JOptionPane.showMessageDialog(null, "Your  password is not  match with current password");
            	} 
             	else if (Address.getText().toString().trim().equals(player.getAddress(username)) == true)
             	{
             		JOptionPane.showMessageDialog(null, "Your  Address is same with current Address");
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
						  player.setAddress(username, Address.getText().toString().trim());
						  JOptionPane.showMessageDialog(null, "You have changed your Address succesfully,you have to login again!");
						  dispose();
						  Home ex = new Home();
							ex.setVisible(true);
				          
								
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
		setTitle("Changing your address");
		setSize(ResolutionSetting.x, ResolutionSetting.y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
