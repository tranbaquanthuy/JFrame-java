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

public class Coupon extends JFrame
{
	private JFrame frame;
	public Coupon()
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
	btnLogout.setText("Back");
	btnLogout.setVisible(true);
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
    JLabel lblNewLabel_1 = new JLabel();
    add(lblNewLabel_1);
    lblNewLabel_1.setBounds(0, 109, 314, 25);
    lblNewLabel_1.setText("Enter your coupon you want to get :");
    
	JTextField text = new JTextField(20);
	add(text);
    text.setBounds(0, 144, 206, 31);
    
    JButton btnSend = new JButton();
    
    JPanel southJPanel = new JPanel(new GridLayout(2, 1, 5, 10));
	
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
			if(text.getText().equals("") )
        	{
				JOptionPane.showMessageDialog(null,"coupon can not be blank");
        	}
        	else
        	{
       	 
       	if (player.checkCoupon(text.getText().toString().trim()) == false)
    	{
       		     int reply = JOptionPane.showConfirmDialog(null,
		            "Are you sure?",
		            "Confirm",
		            JOptionPane.YES_NO_OPTION);
        		    if(reply == JOptionPane.YES_OPTION)
        		    {	
        		    		if(player.getStatusFromCoupon(text.getText().toString().trim()).equals("chuanhan") == true)
        		    		{
        		    		Long moneycoupon  = Long.parseLong(player.getMoneyFromCoupon(text.getText().toString().trim()));
        		    		player.addCouponMoneyToBank(String.valueOf(player.getAccountBankNumber(username.trim())), moneycoupon.toString());
        		    		player.setStateOfCoupon(player.getAccountBankNumber(username.trim()), text.getText().toString().trim());
        		    		lblMoney.setText("Your Money         : " + String.valueOf(player.getMoney(username.trim())) + "$");
        		    		text.setText("");
                			JOptionPane.showMessageDialog(null,"Get coupon successfully");	
        		    		}
        		    		else
        		    		{
        		    			JOptionPane.showMessageDialog(null,"Your coupon is already used");	
        		    		}
        		    	}
        			//player.transferMoney(username, text.getText().toString(), text_1.getText().toString());
        		       
        		} 
       	else 
    	{
    		JOptionPane.showMessageDialog(null,"Your coupon is invalid");	
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
    btnSend.setBounds(0, 235, 140, 50);
    btnSend.setText("Get");
	//add(btnSend);
	
	add(panel);
	setTitle("Coupon");
	setSize(ResolutionSetting.x, ResolutionSetting.y);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
