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
import javax.swing.JTextField;



public class TransferScreen extends JFrame
{
	private JFrame frame;
	public TransferScreen()
	{
		frame = new JFrame();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - 700) / 2);
	    int y = (int) ((dimension.getHeight() - 500) / 2);
		JPanel panel = new JPanel();
		 
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
 
		panel.setLayout(new GridLayout(x, y, 50, 50));
	     
		ConnectSQL player = new ConnectSQL();
		String username =  Login.Username();
		JLabel lblUsername = new JLabel("Your Username : " + username);
		lblUsername.setSize(150, 50);
		lblUsername.setBounds(0, 0, 150, 50);
		lblUsername.setVisible(true); 
        add(lblUsername);
        JLabel lblABnumber = new JLabel("Your Bank Number : " + String.valueOf(player.getAccountBankNumber(username.trim())));
        lblABnumber.setSize(220, 50);
        lblABnumber.setBounds(0, 40, 220, 50);
        lblABnumber.setVisible(true); 
        add(lblABnumber);
        
        JLabel lblEmail = new JLabel("Your Email : " + String.valueOf(player.getEmail(username.trim())));
        lblEmail.setSize(220, 50);
        lblEmail.setBounds(0, 60, 220, 50);
        lblEmail.setVisible(true); 
        add(lblEmail);
        JLabel lblMoney = new JLabel("Your Money         : " + String.valueOf(player.getMoney(username.trim())) + "$");
        lblMoney.setSize(180, 50);
        lblMoney.setBounds(0, 20, 180, 50);
        lblMoney.setVisible(true); 
        add(lblMoney);
        
        JButton btnLogout = new JButton();
		//btnLogout.setIcon(loginImage);
		btnLogout.setSize(60,140);
		btnLogout.setText("Back");
		btnLogout.setVisible(true);
		btnLogout.setBounds(0, 290, 140, 50);
		btnLogout.addActionListener(
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
		add(btnLogout);
		
		
		JTextField text = new JTextField(20);
		add(text);
        text.setBounds(0, 144, 206, 31);
        
        JLabel lblNewLabel_1 = new JLabel();
        add(lblNewLabel_1);
        lblNewLabel_1.setBounds(0, 109, 314, 25);
        lblNewLabel_1.setText("Enter your money you want to send :");
        
        JLabel lblNewLabel_2 = new JLabel();
        add(lblNewLabel_2);
        lblNewLabel_2.setBounds(0, 175, 303, 25);
      lblNewLabel_2.setText("Enter account bank you want to send :");
        
        JTextField text_1 = new JTextField(20);
        text_1.setBounds(0, 200, 206, 31);
        add(text_1);
        JButton btnSend = new JButton();
        
        JPanel southJPanel = new JPanel(new GridLayout(2, 3, 5, 10));
    	
    	JPanel southJPanelsub = new JPanel(new BorderLayout());
    	
    	add(southJPanelsub,BorderLayout.SOUTH);
    	southJPanelsub.add(southJPanel, BorderLayout.WEST);
    	southJPanelsub.setPreferredSize(new Dimension(150, 100));
    	southJPanelsub.setMinimumSize(new Dimension(150, 100));
    	southJPanel.add(btnSend );
    	southJPanel.add(btnLogout );
    
        btnSend.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 if(player.checkInternet() == true )
	              {
				if(text.getText().equals("") || text_1.getText().equals(""))
	        	{
					JOptionPane.showMessageDialog(null,"money or account bank can not be blank");
	        	}
	        	else
	        		
	        	{
	        	   try { 
	        	    Long tienchuyen = Long.parseLong(text.getText().toString());
	        	   
	        		if(player.checkMatvData(text_1.getText().toString()) == false)
	        				{
	        			JOptionPane.showMessageDialog(null,"bank number is not exist");
	        				}
	        		else if(player.getMatvData(username).equals(text_1.getText().toString()))
	        		{
	        			JOptionPane.showMessageDialog(null,"can not transfer to your own bank");
	        		}
	        		else 
	        		{
	        			 if (tienchuyen > player.getMoney(username.trim()) )
	        			 {
	        	           JOptionPane.showMessageDialog(null,"Your money you want to send is higher than your current money");
	        			 }
	        			 else {
	        				
	        				 
	        				 int reply = JOptionPane.showConfirmDialog(null,
	        				            "Are you sure?",
	        				            "Confirm",
	        				            JOptionPane.YES_NO_OPTION);

	        		    if(reply == JOptionPane.YES_OPTION)
	        		    {	 
	        			player.transferMoney(username, text.getText().toString(), text_1.getText().toString());
	        			lblMoney.setText("Your Money         : " + String.valueOf(player.getMoney(username.trim())) + "$");
	        			JOptionPane.showMessageDialog(null,"Transfer successfully");	
	        			}
	        		    
	        			 }
	        		}
	        	   } catch (Exception e1) { 
	        			JOptionPane.showMessageDialog(null,"Please check your money you want to send");
	   	        }
	        	}
			}
				 else
					{
						JOptionPane.showMessageDialog(null, "lost internet connection, please check your internet!");
						  dispose();
					      Login nextframe= new Login();
	                      nextframe.setVisible(true);
					}
			}
			
        });
        btnSend.setBounds(0, 240, 140, 50);
        btnSend.setText("Send ");
	//	add(btnSend);
		
		add(panel);
		setTitle("Transfer Zone");
		setSize(ResolutionSetting.x, ResolutionSetting.y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
