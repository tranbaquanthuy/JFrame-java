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

public class Register extends JFrame {

	private JFrame frame;
	public Register() {
		frame = new JFrame();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - 700) / 2);
	    int y = (int) ((dimension.getHeight() - 500) / 2);
		JPanel panel = new JPanel();
		 
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
 
		panel.setLayout(new GridLayout(x, y, 50, 50));
	     
		ConnectSQL player = new ConnectSQL();
		
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
						   dispose();
					      Login nextframe= new Login();
	                      nextframe.setVisible(true);
		            } 
		         } 
		      ); 
		
		
		JButton btnAssignRegister = new JButton();
		btnAssignRegister.setSize(60,140);
		btnAssignRegister.setText("Register");
		btnAssignRegister.setVisible(true);
		btnAssignRegister.setBounds(0, 240, 140, 50);
		
		add(btnAssignRegister);
	
		
		JLabel lblUsername = new JLabel("Enter Username       :");
		lblUsername.setSize(80, 25);
		lblUsername.setBounds(0, 0, 120, 30);
		lblUsername.setVisible(true); 
        add(lblUsername);
        
        JTextField uSN = new JTextField(15);
		uSN.setSize(140, 25);
		uSN.setBounds(120, 0, 120, 30);
		uSN.setVisible(true);
		add(uSN);
		
		JLabel lblpassword = new JLabel("Enter Password       :");
		lblpassword.setSize(80, 25);
		lblpassword.setBounds(0, 35, 120, 30);
		lblpassword.setVisible(true); 
        add(lblpassword);
        
        JPasswordField password = new JPasswordField();
        password.setSize(140, 25);
        password.setBounds(120, 35, 120, 30);
        password.setVisible(true);
		add(password);
		
		JLabel lblrpassword = new JLabel("Re-enter Password :");
		lblrpassword.setSize(80, 25);
		lblrpassword.setBounds(0, 70, 120, 30);
		lblrpassword.setVisible(true); 
        add(lblrpassword);
        
        JPasswordField rpassword = new JPasswordField();
        rpassword.setSize(140, 25);
        rpassword.setBounds(120, 70, 120, 30);
        rpassword.setVisible(true);
		add(rpassword);
		
		JLabel lblEmail = new JLabel("Enter Your Email      :");
		lblEmail.setSize(80, 25);
		lblEmail.setBounds(0, 105, 120, 30);
		lblEmail.setVisible(true); 
        add(lblEmail);
        
        JTextField Email = new JTextField(15);
        Email.setSize(140, 25);
		Email.setBounds(120, 105, 120, 30);
		Email.setVisible(true);
		add(Email);
		
		JLabel lblAddress = new JLabel("Enter Your Address:");
		lblAddress.setSize(80, 25);
		lblAddress.setBounds(0, 140, 120, 30);
		lblAddress.setVisible(true); 
        add(lblAddress);
        
        JTextField address = new JTextField(50);
        address.setSize(140, 25);
        address.setBounds(120, 140, 120, 30);
        address.setVisible(true);
		add(address);
		
		btnAssignRegister.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
						if(player.checkInternet() == true )
			              {
							if(uSN.getText().isEmpty()  )
							{
								System.out.println("blank");
								JOptionPane.showMessageDialog(null, "Your username is blank");
							}
							else if(uSN.getText().toString().length() > 10)
							{
								JOptionPane.showMessageDialog(null, "Your username have to less than 10 characters");
							}
			            	else if (password.getPassword().length <= 0)  
			            	{
			            		System.out.println("blank");
								JOptionPane.showMessageDialog(null, "Your password is blank");
			            	}
			            	else if (password.getPassword().length > 10)  
			            	{
								JOptionPane.showMessageDialog(null, "Your password have to less than 10 characters");
			            	}
			            	else if (rpassword.getPassword().length <= 0)  
			            	{
			            		System.out.println("blank");
								JOptionPane.showMessageDialog(null, "Your re-enter password is blank");
			            	}
			            	else if (Email.getText().isEmpty())  
			            	{
			            		System.out.println("blank");
								JOptionPane.showMessageDialog(null, "Your email is blank");
			            	}
			            	else if (Email.getText().toString().length() > 30)  
			            	{
								JOptionPane.showMessageDialog(null, "Your email have to less than 30 characters");
			            	}
			            	else if (player.checkUSN(uSN.getText().toString().trim()) == true)
			            	{
								JOptionPane.showMessageDialog(null, "Your username has already taken");
			            	}
			            	else if (player.checkEmail(Email.getText().toString().trim()) == true)
			            	{
								JOptionPane.showMessageDialog(null, "Your email has already registered");
			            	}
			            	else if  ( Email.getText().toString().contains("@") == false || Email.getText().toString().contains(".") == false)
			            	{
			            		JOptionPane.showMessageDialog(null, "Your email is invalid");
			            	}
			            	else
			            	{
			            		//uSN.setText(player.createbanknumber());
			            		int reply = JOptionPane.showConfirmDialog(
			    			            null,
			    			            "Are you sure?",
			    			            "Confirm",
			    			            JOptionPane.YES_NO_OPTION);
			            
			    			        if(reply == JOptionPane.YES_OPTION){
			    			        	   if(player.checkInternet() == true )
			    				              {
			    			     //  if(address.getText().isEmpty())
			    			   //    {
			    			    //	   address.setText("");
			    			    //   }
			            		String banknumber = player.createbanknumber();
			            		player.addNewUser(uSN.getText().toString(), password.getText().toString(), 
			            		Email.getText().toString(), address.getText().toString(), banknumber.trim());
			            		String money = "2000";
			            		player.addNewBankAccount(banknumber, money);
			            		if(player.checkUSN(uSN.getText().toString()))
			            		{
			            		JOptionPane.showMessageDialog(null, "Your account has created sucessfully,you recieved 2000 in your bank account, your bank number is" + banknumber);
			            		dispose();
							      Login nextframe= new Login();
			                      nextframe.setVisible(true);
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
		            } 
		            }
		         } 
		      ); 
		
		add(panel);
		 
		setTitle("Register");
		setSize(ResolutionSetting.x, ResolutionSetting.y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
